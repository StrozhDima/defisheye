/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

import boofcv.alg.distort.AdjustmentType;
import boofcv.alg.distort.ImageDistort;
import boofcv.alg.distort.LensDistortionNarrowFOV;
import boofcv.alg.distort.LensDistortionOps;
import boofcv.alg.distort.LensDistortionWideFOV;
import boofcv.alg.distort.NarrowToWidePtoP_F32;
import boofcv.alg.distort.PointToPixelTransform_F32;
import boofcv.alg.distort.pinhole.LensDistortionPinhole;
import boofcv.alg.distort.universal.LensDistortionUniversalOmni;
import boofcv.alg.interpolate.InterpolatePixel;
import boofcv.alg.interpolate.InterpolationType;
import boofcv.core.image.border.BorderType;
import boofcv.factory.distort.FactoryDistort;
import boofcv.factory.interpolate.FactoryInterpolation;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.calib.CameraPinhole;
import boofcv.struct.calib.CameraPinholeRadial;
import boofcv.struct.calib.CameraUniversalOmni;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayU8;
import boofcv.struct.image.ImageType;
import boofcv.struct.image.Planar;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 *
 * @author dzmitry
 */
public class Corrector {

	private static final Logger logger = Logger.getGlobal();

	/**
	 * Execute correction diagonal fisheye
	 *
	 * @param image
	 * @param fisheyeModel
	 * @return BufferedImage
	 */
	static public BufferedImage calibrationDiagonal(BufferedImage image, CameraPinholeRadial fisheyeModel) {
		logger.info("Called function calibrationDiagonal()");
		// загружаем параметры калибровки из ранее откалиброванной камеры
		// и указываем преобразование не имеющее искажений на которое будем отображать изображение
		CameraPinhole desired = new CameraPinhole(fisheyeModel);
		// загружаем изображение и конвертируем в формат Planar
		Planar<GrayF32> distortedImg = ConvertBufferedImage.convertFromPlanar(image, null, true, GrayF32.class);
		// получаем количество цветов изображения
		int numBands = distortedImg.getNumBands();
		// создаем новое преобразование, которое оптимизируем зону просмотра
		// AdjustmentType.EXPAND гарантирует, что в изображении нет черного цвета за пределами пикселей изображения
		// AdjustmentType.FULL_VIEW будет включать все исходное изображение
		// BorderType - значение границы, по умолчанию она черная, так что она видна
		ImageDistort allInside = LensDistortionOps.changeCameraModel(AdjustmentType.EXPAND, BorderType.ZERO, fisheyeModel, desired, null, ImageType.pl(numBands, GrayF32.class));
		// создаем изображение в формате Planar с указанными свойствами
		Planar<GrayF32> undistortedImg = new Planar<>(GrayF32.class, distortedImg.getWidth(), distortedImg.getHeight(), distortedImg.getNumBands());
		// применим преобразование ко всему исходному изображению в формате Planar
		allInside.apply(distortedImg, undistortedImg);
		// конвертируем из формата Planar в формат BufferedImage
		return ConvertBufferedImage.convertTo(undistortedImg, null, true);
	}

	/**
	 * Execute correction circular fisheye
	 *
	 * @param image
	 * @param fisheyeModel
	 * @return BufferedImage
	 */
	static public BufferedImage calibrationCircular(BufferedImage image, CameraUniversalOmni fisheyeModel) {
		logger.info("Called function calibrationCircular()");
		// загружаем параметры калибровки из ранее откалиброванной камеры
		// и указываем преобразование не имеющее искажений на которое будем отображать изображение
		CameraPinhole pinholeModel = new CameraPinhole(fisheyeModel);
		// загружаем изображение и конвертируем в формат Planar
		Planar<GrayU8> fisheyeImage = ConvertBufferedImage.convertFrom(image, true, ImageType.pl(3, GrayU8.class));
		// создаем парамеры трансформации
		LensDistortionNarrowFOV pinholeDistort = new LensDistortionPinhole(pinholeModel);
		LensDistortionWideFOV fisheyeDistort = new LensDistortionUniversalOmni(fisheyeModel);
		NarrowToWidePtoP_F32 transform = new NarrowToWidePtoP_F32(pinholeDistort, fisheyeDistort);
		// создаем объект ImageDistort, на который будем накладывать исходное изображение
		InterpolatePixel<Planar<GrayU8>> interp = FactoryInterpolation.createPixel(0, 255, InterpolationType.BILINEAR, BorderType.ZERO, fisheyeImage.getImageType());
		ImageDistort<Planar<GrayU8>, Planar<GrayU8>> distorter = FactoryDistort.distort(false, interp, fisheyeImage.getImageType());
		// установить парамеры трансформации, созданные выше
		distorter.setModel(new PointToPixelTransform_F32(transform));
		// создаем новое изображение. Камера не будет повернута и будет смотреть прямо
		Planar<GrayU8> pinholeImage = fisheyeImage.createNew(pinholeModel.width, pinholeModel.height);
		// повернуть камеру вправо
		//transform.setRotationWideToNarrow(ConvertRotation3D_F32.eulerToMatrix(EulerType.YXZ, 0.8f, 0, 0, null));
		// применяем искажения
		distorter.apply(fisheyeImage, pinholeImage);
		return ConvertBufferedImage.convertTo(pinholeImage, null, true);
	}

}
