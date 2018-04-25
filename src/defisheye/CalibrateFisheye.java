/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

import boofcv.abst.fiducial.calib.ConfigChessboard;
import boofcv.abst.fiducial.calib.ConfigCircleHexagonalGrid;
import boofcv.abst.fiducial.calib.ConfigCircleRegularGrid;
import boofcv.abst.fiducial.calib.ConfigSquareGrid;
import boofcv.abst.geo.calibration.CalibrateMonoPlanar;
import boofcv.abst.geo.calibration.DetectorFiducialCalibration;
import boofcv.factory.fiducial.FactoryFiducialCalibration;
import boofcv.gui.BoofSwingUtil;
import boofcv.io.UtilIO;
import boofcv.io.calibration.CalibrationIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.calib.CameraPinholeRadial;
import boofcv.struct.calib.CameraUniversalOmni;
import boofcv.struct.image.GrayF32;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author dzmitry
 */
public class CalibrateFisheye {

	private static final Logger logger = Logger.getGlobal();

	/**
	 * Get detector Circle regular grid
	 *
	 * @param numRows
	 * @param numCols
	 * @param circleDiameter
	 * @param centerDistance
	 * @return DetectorFiducialCalibration
	 */
	static public DetectorFiducialCalibration getDetectorCircleRegularGrid(int numRows, int numCols, double circleDiameter, double centerDistance) {
		// Circle Regular Grid Example (8, 10, 1.5, 2.5)
		return FactoryFiducialCalibration.circleRegularGrid(new ConfigCircleRegularGrid(numRows, numCols, circleDiameter, centerDistance));
	}

	/**
	 * Get detector Circle hexagonal grid
	 *
	 * @param numRows
	 * @param numCols
	 * @param centerDistance
	 * @param circleDiameter
	 * @return DetectorFiducialCalibration
	 */
	static public DetectorFiducialCalibration getDetectorCircleHexagonalGrid(int numRows, int numCols, double circleDiameter, double centerDistance) {
		// Hexagonal Circle Example (24, 28, 1, 1.2)
		return FactoryFiducialCalibration.circleHexagonalGrid(new ConfigCircleHexagonalGrid(numRows, numCols, circleDiameter, centerDistance));
	}

	/**
	 * Get detector Square grid
	 *
	 * @param numRows
	 * @param numCols
	 * @param squareWidth
	 * @param spaceWidth
	 * @return DetectorFiducialCalibration
	 */
	static public DetectorFiducialCalibration GetDetectorSquareGrid(int numRows, int numCols, double squareWidth, double spaceWidth) {
		// Square Grid example (4, 3, 30, 30)
		return FactoryFiducialCalibration.squareGrid(new ConfigSquareGrid(numRows, numCols, squareWidth, spaceWidth));

	}

	/**
	 * Get detector Chessboard
	 *
	 * @param numRows
	 * @param numCols
	 * @param squareWidth
	 * @return DetectorFiducialCalibration
	 */
	static public DetectorFiducialCalibration GetDetectorChessboard(int numRows, int numCols, double squareWidth) {
		// Chessboard Example (7, 5, 30)
		return FactoryFiducialCalibration.chessboard(new ConfigChessboard(numRows, numCols, squareWidth));
	}

	/**
	 * Get FullFrameParams parameters
	 *
	 * @param component
	 * @param folderPath
	 * @param detector
	 * @return CameraPinholeRadial
	 */
	static public CameraPinholeRadial getDaigonalParams(Component component, String folderPath, DetectorFiducialCalibration detector) {
		logger.info("Called function getFullFrameParams()");
		// добавление изображений в список
		List<String> images = UtilIO.listAll(UtilIO.pathExample(folderPath));
		// обявление и инициализация алгоритма калибровки
		CalibrateMonoPlanar calibrationAlg = new CalibrateMonoPlanar(detector.getLayout());
		// устанавливаем тип и какие параметры оценить (4 параметр исправляет смещение зеркала)
		calibrationAlg.configurePinhole(true, 2, false);
		// проходимся по списку изображений и обнаруживаем точки на каждом из них
		images.forEach((n) -> {
			BufferedImage input = UtilImageIO.loadImage(n);
			if (input != null) {
				GrayF32 image = ConvertBufferedImage.convertFrom(input, (GrayF32) null);
				if (detector.process(image)) {
					calibrationAlg.addImage(detector.getDetectedPoints().copy());
				} else {
					BoofSwingUtil.warningDialog(component, new InvalidParameterException("Failed to detect target in" + n));
					logger.warning("getFullFrameParams(): Failed to detect target in " + n);
				}
			}
		});
		// обрабатываем точки и вычисляем внутренние параметры камеры
		CameraPinholeRadial intrinsic = calibrationAlg.process();
		logger.info("\nfx:" + intrinsic.fx + "\nfy:" + intrinsic.fy + "\ncx:" + intrinsic.cx + "\ncy:" + intrinsic.cy + "\nskew:" + intrinsic.skew);
		// сохраняем результаты в файл
		CalibrationIO.save(intrinsic, folderPath + "/fisheye.yaml");
		return intrinsic;
	}

	/**
	 * Get camera circular parameters
	 *
	 * @param folderPath
	 * @param detector
	 * @return CameraUniversalOmni
	 */
	static public CameraUniversalOmni getCircularParams(Component component, String folderPath, DetectorFiducialCalibration detector) {
		logger.info("Called function getCircularParams()");
		// добавление изображений в список
		List<String> images = UtilIO.listAll(UtilIO.pathExample(folderPath));
		// обявление и инициализация алгоритма калибровки
		CalibrateMonoPlanar calibrationAlg = new CalibrateMonoPlanar(detector.getLayout());
		// устанавливаем тип и какие параметры оценить (4 параметр исправляет смещение зеркала)
		calibrationAlg.configureUniversalOmni(true, 2, false);
		// проходимся по списку изображений и обнаруживаем точки на каждом из них
		images.forEach((n) -> {
			BufferedImage input = UtilImageIO.loadImage(n);
			if (input != null) {
				GrayF32 image = ConvertBufferedImage.convertFrom(input, (GrayF32) null);
				if (detector.process(image)) {
					calibrationAlg.addImage(detector.getDetectedPoints().copy());
				} else {
					BoofSwingUtil.warningDialog(component, new InvalidParameterException("Failed to detect target in" + n));
					logger.warning("getCircularParams(): Failed to detect target in " + n);
				}
			}
		});
		// обрабатываем точки и вычисляем внутренние параметры камеры
		CameraUniversalOmni intrinsic = calibrationAlg.process();
		logger.info("\nfx:" + intrinsic.fx + "\nfy:" + intrinsic.fy + "\ncx:" + intrinsic.cx + "\ncy:" + intrinsic.cy + "\nskew:" + intrinsic.skew);
		// сохраняем результаты в файл
		CalibrationIO.save(intrinsic, folderPath + "/fisheye.yaml");
		return intrinsic;
	}
}
