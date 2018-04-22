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
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Dzmitry
 */
public class Controller {

	private static final Logger logger = Logger.getGlobal();
	private final Model model;
	private final View view;
	private final CalibView calibView;
	private File imageFile;
	private File pathPalnars;
	private BufferedImage image;

	/**
	 * Constructor initialize
	 *
	 * @param m
	 * @param v
	 */
	public Controller(Model m, View v) {
		model = m;
		view = v;
		this.view.initView();
		this.calibView = this.view.getCalibUI();
	}

	/**
	 * Initialize Controller (listeners)
	 */
	public void initController() {

		//обработка нажатия пункта меню File -> Open...
		this.view.getMenuItemOpen().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Open...'");
			this.model.setDialogTitle(Constants.OPEN_FILECHOOSER);
			this.model.setFileSelectionMode(Model.FILES_ONLY);
			int result = this.model.showOpenDialog(this.view);
			if (result == Model.APPROVE_OPTION) {
				this.imageFile = this.model.getSelectedFile();
				this.view.getMenuItemClose().setEnabled(true);
				this.view.getMenuItemSave().setEnabled(true);
				this.view.getMenuItemSaveAs().setEnabled(true);
				this.view.getMenuItemReset().setEnabled(true);
				logger.info("File is: " + imageFile.toString());
			} else {
				logger.severe("Can't load file" + imageFile.toString());
			}
			try {
				image = ImageIO.read(imageFile);
				BufferedImage scaled = scale(image, this.view.getImageLabel().getWidth(), this.view.getImageLabel().getHeight());
				this.view.getImageLabel().setIcon(new ImageIcon(scaled));
				logger.info("Set image: " + imageFile.toString());
			} catch (IOException ex) {
				logger.severe((Supplier<String>) ex);
			}
		});

		//обработка нажатия пункта меню File -> Close
		this.view.getMenuItemClose().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Close'");
			this.imageFile = null;
			this.view.setDefaultImageLabel();
			this.view.getMenuItemClose().setEnabled(false);
			this.view.getMenuItemSave().setEnabled(false);
			this.view.getMenuItemSaveAs().setEnabled(false);
			this.view.getMenuItemReset().setEnabled(false);
		});

		//обработка нажатия пункта меню File -> Save
		this.view.getMenuItemSave().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Save'");
			try {
				ImageIO.write(this.image, this.model.getTypeOfFile(this.imageFile), this.imageFile);
			} catch (IOException ex) {
				logger.severe((Supplier<String>) ex);
			}
			logger.info("Save file:" + this.imageFile + ". Type: " + this.model.getTypeOfFile(this.imageFile));
		});

		//обработка нажатия пункта меню File -> Save as...
		this.view.getMenuItemSaveAs().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Save as...'");
			this.model.setDialogTitle(Constants.SAVE_FILECHOOSER);
			this.model.setFileSelectionMode(Model.FILES_ONLY);
			int result = this.model.showSaveDialog(this.view);
			if (result == Model.APPROVE_OPTION) {
				this.imageFile = this.model.getSelectedFile();
				try {
					ImageIO.write(this.image, this.model.getTypeOfFile(this.imageFile), this.imageFile);
				} catch (IOException ex) {
					logger.severe((Supplier<String>) ex);
				}
				logger.info("File save as : " + this.imageFile.toString());
			} else {
				logger.severe("Can't save file as: " + this.imageFile.toString());
			}
		});

		//обработка нажатия пункта меню File -> Exit
		this.view.getMenuItemExit().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Exit'");
			System.exit(0);
		});

		//обработка нажатия пункта меню Edit -> Undo
		this.view.getMenuItemUndo().addActionListener((ActionEvent e) -> {

		});

		//обработка нажатия пункта меню Edit -> Redo
		this.view.getMenuItemRedo().addActionListener((ActionEvent e) -> {

		});

		//обработка нажатия пункта меню Edit -> Reset
		this.view.getMenuItemReset().addActionListener((ActionEvent e) -> {

		});

		//обработка нажатия пункта меню Help -> About
		this.view.getMenuItemAbout().addActionListener((ActionEvent e) -> {

		});

		//обработка нажатия пункта меню Help -> Help
		this.view.getMenuItemHelp().addActionListener((ActionEvent e) -> {

		});

		/*Работа с модулем автоматической калибровки камеры*/
		//обработка нажатия пункта меню File -> Open...
		this.calibView.getMenuItemOpenCalib().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Open...'");
			this.model.setDialogTitle(Constants.CALIB_OPENFILECHOOSER);
			this.model.setFileSelectionMode(Model.DIRECTORIES_ONLY);
			int result = this.model.showOpenDialog(this.calibView);
			if (result == Model.APPROVE_OPTION) {
				this.pathPalnars = this.model.getSelectedFile();
				this.view.getMenuItemClose().setEnabled(true);
				logger.info("Directory is: " + this.pathPalnars);
			} else {
				logger.severe("Can't get directory: " + this.pathPalnars);
			}
			this.calibView.getTextFieldCalibPathExamples().setText(this.pathPalnars.toString());
			this.calibView.getMenuItemCloseCalib().setEnabled(true);
		});

		//обработка нажатия пункта меню File -> Close
		this.calibView.getMenuItemCloseCalib().addActionListener((ActionEvent e) -> {
			logger.info("Pressed calib menu item 'File -> Close'");
			this.pathPalnars = null;
			this.calibView.getMenuItemCloseCalib().setEnabled(false);
			this.calibView.getTextFieldCalibPathExamples().setText("");
		});

		//обработка нажатия пункта меню File -> Exit
		this.calibView.getMenuItemExitCalib().addActionListener((ActionEvent e) -> {
			logger.info("Pressed calib menu item 'File -> Exit'");
			this.calibView.dispose();
			this.pathPalnars = null;
			this.calibView.getMenuItemCloseCalib().setEnabled(false);
			this.calibView.getTextFieldCalibPathExamples().setText("");
		});

		//обработка нажатия кнопки "Run"
		this.calibView.getButtonCalibRun().addActionListener((ActionEvent e) -> {
			logger.info("Pressed calib menu item 'File -> Close'");
			CalibrateFisheye calibrateFisheye;
			int centerDistance = (Integer) this.calibView.getSpinnerCalibCenterDistance().getValue();
			int cols = (Integer) this.calibView.getSpinnerCalibCols().getValue();
			int rows = (Integer) this.calibView.getSpinnerCalibRows().getValue();
			int spaceWidth = (Integer) this.calibView.getSpinnerCalibSpaceWidth().getValue();
			int squareWidth = (Integer) this.calibView.getSpinnerCalibSquareWidth().getValue();
			int circleDiametr = (Integer) this.calibView.getSpinnerCalibCircleDiametr().getValue();
			String path = this.calibView.getTextFieldCalibPathExamples().getText();

			switch (this.calibView.getComboBoxCalibPlanarType().getSelectedItem().toString()) {
				case Constants.PLANAR_CHESSBOARD:
					//7, 5, 30
					calibrateFisheye = new CalibrateFisheye(new ConfigChessboard(rows, cols, squareWidth), path);
					calibrateFisheye.calculateMonocularParams();
					break;

				case Constants.PLANAR_CIRCLE_GRID:
					calibrateFisheye = new CalibrateFisheye(new ConfigCircleRegularGrid(rows, cols, circleDiametr, centerDistance), path);
					break;

				case Constants.PLANAR_CIRCLE_HEX:
					calibrateFisheye = new CalibrateFisheye(new ConfigCircleHexagonalGrid(rows, cols, circleDiametr, centerDistance), path);
					break;

				case Constants.PLANAR_SQUARE_GRIDS:
					calibrateFisheye = new CalibrateFisheye(new ConfigSquareGrid(rows, cols, squareWidth, spaceWidth), path);
					break;

				default:
					throw new IllegalArgumentException("Invalid type of planar");
			}
		});

		//обработка выбора типа планара
		this.calibView.getComboBoxCalibPlanarType().addActionListener((ActionEvent e) -> {
			logger.info("Pressed comboBox 'Planar type'");
			switch (this.calibView.getComboBoxCalibPlanarType().getSelectedItem().toString()) {
				case Constants.PLANAR_CHESSBOARD:
					this.calibView.getSpinnerCalibSquareWidth().setEnabled(false);
					this.calibView.getSpinnerCalibCircleDiametr().setEnabled(false);
					this.calibView.getSpinnerCalibCenterDistance().setEnabled(false);
					this.calibView.getSpinnerCalibSpaceWidth().setEnabled(false);
					break;

				case Constants.PLANAR_CIRCLE_GRID:
					this.calibView.getSpinnerCalibCircleDiametr().setEnabled(true);
					this.calibView.getSpinnerCalibCenterDistance().setEnabled(false);
					this.calibView.getSpinnerCalibSpaceWidth().setEnabled(false);
					this.calibView.getSpinnerCalibSquareWidth().setEnabled(false);
					break;

				case Constants.PLANAR_CIRCLE_HEX:
					this.calibView.getSpinnerCalibCircleDiametr().setEnabled(true);
					this.calibView.getSpinnerCalibCenterDistance().setEnabled(true);
					this.calibView.getSpinnerCalibSpaceWidth().setEnabled(false);
					this.calibView.getSpinnerCalibSquareWidth().setEnabled(false);
					break;

				case Constants.PLANAR_SQUARE_GRIDS:
					this.calibView.getSpinnerCalibSquareWidth().setEnabled(true);
					this.calibView.getSpinnerCalibSpaceWidth().setEnabled(true);
					this.calibView.getSpinnerCalibCircleDiametr().setEnabled(false);
					this.calibView.getSpinnerCalibCenterDistance().setEnabled(false);
					break;

				default:
					throw new IllegalArgumentException("Invalid type of planar");
			}
		});
	}

	/**
	 * Scale BufferedImage
	 *
	 * @param scr
	 * @param w
	 * @param h
	 */
	private BufferedImage scale(BufferedImage src, int w, int h) {
		BufferedImage dst = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = dst.createGraphics();
		g2.clearRect(0, 0, w, h);
		double xScale = (double) w / src.getWidth();
		double yScale = (double) h / src.getHeight();
		// Scaling options:
		// Scale to fit - image just fits in label.
		double scale = Math.min(xScale, yScale);
		// Scale to fill - image just fills label.
		//double scale = Math.max(xScale, yScale);
		int width = (int) (scale * src.getWidth());
		int height = (int) (scale * src.getHeight());
		int x = (w - width) / 2;
		int y = (h - height) / 2;
		g2.drawImage(src, x, y, width, height, null);
		g2.dispose();
		return dst;
	}

}
