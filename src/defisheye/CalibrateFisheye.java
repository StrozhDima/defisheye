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
import boofcv.io.UtilIO;
import boofcv.io.calibration.CalibrationIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.calib.CameraUniversalOmni;
import boofcv.struct.image.GrayF32;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author dzmitry
 */
public class CalibrateFisheye {

	private static final Logger logger = Logger.getGlobal();
	private DetectorFiducialCalibration detector;
	private ConfigChessboard configChessboard;
	private ConfigSquareGrid configSquareGrid;
	private ConfigCircleHexagonalGrid configCircleHexGrid;
	private ConfigCircleRegularGrid configCircleRegGrid;
	private String path;
	private List<String> images;

	/**
	 * Constructor initialize (Chessboard)
	 *
	 * @param configChessboard
	 * @param path
	 */
	public CalibrateFisheye(ConfigChessboard configChessboard, String path) {
		this.configChessboard = configChessboard;
		this.path = path;
		this.detector = FactoryFiducialCalibration.chessboard(configChessboard);
	}

	/**
	 * Constructor initialize (SquareGrid)
	 *
	 * @param configSquareGrid
	 * @param path
	 */
	public CalibrateFisheye(ConfigSquareGrid configSquareGrid, String path) {
		this.configSquareGrid = configSquareGrid;
		this.path = path;
		this.detector = FactoryFiducialCalibration.squareGrid(this.configSquareGrid);
	}

	/**
	 * Constructor initialize (CircleHexagonalGrid)
	 *
	 * @param configCircleHexGrid
	 * @param path
	 */
	public CalibrateFisheye(ConfigCircleHexagonalGrid configCircleHexGrid, String path) {
		this.configCircleHexGrid = configCircleHexGrid;
		this.path = path;
		this.detector = FactoryFiducialCalibration.circleHexagonalGrid(this.configCircleHexGrid);
	}

	/**
	 * Constructor initialize (ConfigCircleRegularGrid)
	 *
	 * @param configCircleRegGrid
	 * @param path
	 */
	public CalibrateFisheye(ConfigCircleRegularGrid configCircleRegGrid, String path) {
		this.configCircleRegGrid = configCircleRegGrid;
		this.path = path;
		this.detector = FactoryFiducialCalibration.circleRegularGrid(this.configCircleRegGrid);
	}

	public CameraUniversalOmni calculateMonocularParams() {
		logger.info("Called function calculateMonocularParams");
		this.images = UtilIO.listAll(UtilIO.pathExample(this.path));
		// Declare and setup the calibration algorithm
		CalibrateMonoPlanar calibrationAlg = new CalibrateMonoPlanar(this.detector.getLayout());
		// tell it type type of target and which parameters to estimate
		calibrationAlg.configureUniversalOmni(true, 2, false);
		// it's also possible to fix the mirror offset parameter
		// 0 = pinhole camera. 1 = fisheye
		//calibrationAlg.configureUniversalOmni( true, 2, false,1.0);
		for (String n : this.images) {
			BufferedImage input = UtilImageIO.loadImage(n);
			if (input != null) {
				GrayF32 image = ConvertBufferedImage.convertFrom(input, (GrayF32) null);
				if (this.detector.process(image)) {
					calibrationAlg.addImage(this.detector.getDetectedPoints().copy());
				} else {
					logger.severe("Failed to detect target in :" + n);
				}
			}
		}
		// process and compute intrinsic parameters
		CameraUniversalOmni intrinsic = calibrationAlg.process();
		// save results to a file and print out
		CalibrationIO.save(intrinsic, "fisheye.yaml");
		calibrationAlg.printStatistics();
		System.out.println("--- Intrinsic Parameters ---");
		System.out.println();
		intrinsic.print();
		return intrinsic;
	}
}
