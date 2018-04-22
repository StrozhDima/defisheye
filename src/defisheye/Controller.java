/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

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

    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    private final Model model;
    private final View view;
    private final CalibView calibView;
    private File imageFile;
    BufferedImage image;

    public Controller(Model m, View v, CalibView cv) {
        model = m;
        view = v;
        calibView = cv;
        this.view.initView();
        initLogger();
    }

    private void initLogger() {
        try {
            Handler handler = new FileHandler(Constants.LOGPATH);
            logger.addHandler(handler);
            handler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(Constants.LOGFORMAT,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage()
                    );
                }
            });
            logger.addHandler(handler);
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, "Can't use file 'logs.log'");
        }
    }

    public void initController() {

        //обработка нажатия пункта меню File -> Open...
        this.view.getMenuItemOpen().addActionListener((ActionEvent e) -> {
            this.model.setDialogTitle(Constants.OPENFILECHOOSER);
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
            this.imageFile = null;
            this.view.setDefaultImageLabel();
            this.view.getMenuItemClose().setEnabled(false);
            this.view.getMenuItemSave().setEnabled(false);
            this.view.getMenuItemSaveAs().setEnabled(false);
            this.view.getMenuItemReset().setEnabled(false);
            logger.info("Close file and set default image");
        });

        //обработка нажатия пункта меню File -> Save
        this.view.getMenuItemSave().addActionListener((ActionEvent e) -> {

            try {
                ImageIO.write(this.image, this.model.getTypeOfFile(this.imageFile), this.imageFile);
            } catch (IOException ex) {
                logger.severe((Supplier<String>) ex);
            }
            logger.info("Save file:" + this.imageFile + ". Type: " + this.model.getTypeOfFile(this.imageFile));
        });

        //обработка нажатия пункта меню File -> Save as...
        this.view.getMenuItemSaveAs().addActionListener((ActionEvent e) -> {
            this.model.setDialogTitle(Constants.SAVEFILECHOOSER);
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
    }

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
