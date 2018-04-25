/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

import boofcv.gui.BoofSwingUtil;
import boofcv.struct.calib.CameraPinholeRadial;
import boofcv.struct.calib.CameraUniversalOmni;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.function.Supplier;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Dzmitry
 */
public class Controller {

	private static final Logger logger = Logger.getGlobal();
	private final Model model;
	private final View view;
	private final CalibView calibView;
	private CameraPinholeRadial cameraDiagonalParams;
	private CameraUniversalOmni cameraCircularParams;
	private File imageFile;
	private File pathPalnars;
	private BufferedImage image;
	private BufferedImage tempImage;

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
		this.view.getMenuItemSaveSettings().addActionListener((ActionEvent e) -> {

		});

		//обработка нажатия пункта меню Edit -> Redo
		this.view.getMenuItemLoadSettings().addActionListener((ActionEvent e) -> {

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

		//обработка кнопки Apply
		this.view.getButtonApplyChanging().addActionListener((ActionEvent e) -> {

			if (this.view.getRadioButtonDiagonal().isSelected()) {
				logger.info("Pressed button Apply (with RadioButton Circular)");
				setDiagonalParams(
						Double.parseDouble(String.valueOf(this.view.getSpinnerFX().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerFY().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerCX().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerCY().getValue())),
						Integer.parseInt(String.valueOf(this.view.getSpinnerWidth().getValue())),
						Integer.parseInt(String.valueOf(this.view.getSpinnerHeigth().getValue()))
				);
				this.tempImage = new Corrector().calibrationDiagonal(this.image, this.cameraDiagonalParams);
				BufferedImage scaled = scale(this.tempImage, this.view.getImageLabel().getWidth(), this.view.getImageLabel().getHeight());
				this.view.getImageLabel().setIcon(new ImageIcon(scaled));
			} else if (this.view.getRadioButtonCircular().isSelected()) {
				logger.info("Pressed button Apply (with RadioButton Diagonal)");
				setCircularParams(
						Double.parseDouble(String.valueOf(this.view.getSpinnerFX().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerFY().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerCX().getValue())),
						Double.parseDouble(String.valueOf(this.view.getSpinnerCY().getValue())),
						Integer.parseInt(String.valueOf(this.view.getSpinnerWidth().getValue())),
						Integer.parseInt(String.valueOf(this.view.getSpinnerHeigth().getValue()))
				);
				this.tempImage = new Corrector().calibrationCircular(this.image, this.cameraCircularParams);
				BufferedImage scaled = scale(this.tempImage, this.view.getImageLabel().getWidth(), this.view.getImageLabel().getHeight());
				this.view.getImageLabel().setIcon(new ImageIcon(scaled));
			}
		});

		/*Работа с модулем автоматической калибровки камеры*/
		//обработка нажатия пункта меню File -> Open...
		this.calibView.getMenuItemOpenCalib().addActionListener((ActionEvent e) -> {
			logger.info("Pressed menu item 'File -> Open...'");
			this.model.setDialogTitle(Constants.CALIB_OPENFILECHOOSER);
			this.model.setFileSelectionMode(Model.DIRECTORIES_ONLY);
			this.model.setAcceptAllFileFilterUsed(false);
			int result = this.model.showOpenDialog(this.calibView);
			if (result == Model.APPROVE_OPTION) {
				this.pathPalnars = (new File(this.model.getSelectedFile().getAbsolutePath()));
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
			logger.info("Pressed calib button 'Run'");
			double centerDistance = Double.parseDouble(String.valueOf(this.calibView.getSpinnerCalibCenterDistance().getValue()));
			int cols = (Integer) this.calibView.getSpinnerCalibCols().getValue();
			int rows = (Integer) this.calibView.getSpinnerCalibRows().getValue();
			double spaceWidth = Double.parseDouble(String.valueOf(this.calibView.getSpinnerCalibSpaceWidth().getValue()));
			double squareWidth = Double.parseDouble(String.valueOf(this.calibView.getSpinnerCalibSquareWidth().getValue()));
			double circleDiametr = Double.parseDouble(String.valueOf(this.calibView.getSpinnerCalibCircleDiametr().getValue()));
			String path = this.calibView.getTextFieldCalibPathExamples().getText();
			try {
				// проверяем какой тип планарного изображения выбран
				switch (this.calibView.getComboBoxCalibPlanarType().getSelectedItem().toString()) {
					// если chessboard
					case Constants.PLANAR_CHESSBOARD:
						// проверяем какой тип изображения будет обрабатываться
						if (this.view.getRadioButtonDiagonal().isSelected()) {
							this.cameraDiagonalParams = CalibrateFisheye.getDaigonalParams(this.view, path, CalibrateFisheye.GetDetectorChessboard(rows, cols, squareWidth));
							setSettingsValueToSpinners(this.cameraDiagonalParams.getFx(), this.cameraDiagonalParams.getFy(), this.cameraDiagonalParams.getCx(), this.cameraDiagonalParams.getCy(), this.cameraDiagonalParams.getWidth(), this.cameraDiagonalParams.getHeight());
						} else if (this.view.getRadioButtonCircular().isSelected()) {
							this.cameraCircularParams = CalibrateFisheye.getCircularParams(this.view, path, CalibrateFisheye.GetDetectorChessboard(rows, cols, squareWidth));
							setSettingsValueToSpinners(this.cameraCircularParams.getFx(), this.cameraCircularParams.getFy(), this.cameraCircularParams.getCx(), this.cameraCircularParams.getCy(), this.cameraCircularParams.getWidth(), this.cameraCircularParams.getHeight());
						}
						break;
					// если circle regular grid
					case Constants.PLANAR_CIRCLE_GRID:
						//проверяем какой тип изображения будет обрабатываться
						if (this.view.getRadioButtonDiagonal().isSelected()) {
							this.cameraDiagonalParams = CalibrateFisheye.getDaigonalParams(this.view, path, CalibrateFisheye.getDetectorCircleRegularGrid(rows, cols, circleDiametr, centerDistance));
							setSettingsValueToSpinners(this.cameraDiagonalParams.getFx(), this.cameraDiagonalParams.getFy(), this.cameraDiagonalParams.getCx(), this.cameraDiagonalParams.getCy(), this.cameraDiagonalParams.getWidth(), this.cameraDiagonalParams.getHeight());
						} else if (this.view.getRadioButtonCircular().isSelected()) {
							this.cameraCircularParams = CalibrateFisheye.getCircularParams(this.view, path, CalibrateFisheye.getDetectorCircleRegularGrid(rows, cols, circleDiametr, centerDistance));
							setSettingsValueToSpinners(this.cameraCircularParams.getFx(), this.cameraCircularParams.getFy(), this.cameraCircularParams.getCx(), this.cameraCircularParams.getCy(), this.cameraCircularParams.getWidth(), this.cameraCircularParams.getHeight());
						}
						break;
					// если circle hexagonal grid
					case Constants.PLANAR_CIRCLE_HEX:
						//проверяем какой тип изображения будет обрабатываться
						if (this.view.getRadioButtonDiagonal().isSelected()) {
							this.cameraDiagonalParams = CalibrateFisheye.getDaigonalParams(this.view, path, CalibrateFisheye.getDetectorCircleHexagonalGrid(rows, cols, circleDiametr, centerDistance));
							setSettingsValueToSpinners(this.cameraDiagonalParams.getFx(), this.cameraDiagonalParams.getFy(), this.cameraDiagonalParams.getCx(), this.cameraDiagonalParams.getCy(), this.cameraDiagonalParams.getWidth(), this.cameraDiagonalParams.getHeight());
						} else if (this.view.getRadioButtonCircular().isSelected()) {
							this.cameraCircularParams = CalibrateFisheye.getCircularParams(this.view, path, CalibrateFisheye.getDetectorCircleHexagonalGrid(rows, cols, circleDiametr, centerDistance));
							setSettingsValueToSpinners(this.cameraCircularParams.getFx(), this.cameraCircularParams.getFy(), this.cameraCircularParams.getCx(), this.cameraCircularParams.getCy(), this.cameraCircularParams.getWidth(), this.cameraCircularParams.getHeight());
						}
						break;

					case Constants.PLANAR_SQUARE_GRIDS:
						//проверяем какой тип изображения будет обрабатываться
						if (this.view.getRadioButtonDiagonal().isSelected()) {
							this.cameraDiagonalParams = CalibrateFisheye.getDaigonalParams(this.view, path, CalibrateFisheye.GetDetectorSquareGrid(rows, cols, squareWidth, spaceWidth));
							setSettingsValueToSpinners(this.cameraDiagonalParams.getFx(), this.cameraDiagonalParams.getFy(), this.cameraDiagonalParams.getCx(), this.cameraDiagonalParams.getCy(), this.cameraDiagonalParams.getWidth(), this.cameraDiagonalParams.getHeight());
						} else if (this.view.getRadioButtonCircular().isSelected()) {
							this.cameraCircularParams = CalibrateFisheye.getCircularParams(this.view, path, CalibrateFisheye.GetDetectorSquareGrid(rows, cols, squareWidth, spaceWidth));
							setSettingsValueToSpinners(this.cameraCircularParams.getFx(), this.cameraCircularParams.getFy(), this.cameraCircularParams.getCx(), this.cameraCircularParams.getCy(), this.cameraCircularParams.getWidth(), this.cameraCircularParams.getHeight());
						}

						break;
					default:
						logger.warning("Invalid type of planar");
						JOptionPane.showMessageDialog(this.view, "Invalid type of planar", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IllegalArgumentException ex) {
				BoofSwingUtil.warningDialog(this.view, new InvalidParameterException("Wrong parameters!\nPath: " + path + "; rows:" + rows + "; cols" + cols + "; square width:" + squareWidth));
			}
			JOptionPane.showMessageDialog(this.view, "Success!");
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
					logger.warning("Invalid type of planar");
					BoofSwingUtil.warningDialog(this.view, new InvalidParameterException("Invalid type of planar"));
			}
		});
	}

	private void setSettingsValueToSpinners(double fx, double fy, double cx, double cy, int width, int height) {
		this.view.getSpinnerFX().setValue(fx);
		this.view.getSpinnerFY().setValue(fy);
		this.view.getSpinnerCX().setValue(cx);
		this.view.getSpinnerCY().setValue(cy);
		this.view.getSpinnerWidth().setValue(width);
		this.view.getSpinnerHeigth().setValue(height);
	}

	private void setDiagonalParams(double fx, double fy, double cx, double cy, int width, int height) {
		this.cameraDiagonalParams.setFx(fx);
		this.cameraDiagonalParams.setFy(fy);
		this.cameraDiagonalParams.setCx(cx);
		this.cameraDiagonalParams.setCy(cy);
		this.cameraDiagonalParams.setWidth(width);
		this.cameraDiagonalParams.setHeight(height);
	}

	private void setCircularParams(double fx, double fy, double cx, double cy, int width, int height) {
		this.cameraCircularParams.setFx(fx);
		this.cameraCircularParams.setFy(fy);
		this.cameraCircularParams.setCx(cx);
		this.cameraCircularParams.setCy(cy);
		this.cameraCircularParams.setWidth(width);
		this.cameraCircularParams.setHeight(height);
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
