/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

/**
 *
 * @author Dzmitry
 */
public class Constants {
    
    /*FileChooser params*/
    public static final String OPEN_FILECHOOSER = "Choose image file...";
    public static final String CALIB_OPENFILECHOOSER = "Choose folder with cabration images...";
    public static final String SAVE_FILECHOOSER = "Save image file...";

    /*logger params*/
    public static final String LOG_PATH = "logs.log";
    public static final String LOG_FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

    /*labels pathes*/
    public static final String DEFAULT_LABEL = "/defisheye/icon-image-500.png";
    public static final String DEFAULT_PLANAR_LABEL = "/defisheye/icon-image-250.png";

    /*types of planar images*/
    public static final String PLANAR_CHESSBOARD = "Chessboard";
    public static final String PLANAR_SQUARE_GRIDS = "Square Grids";
    public static final String PLANAR_CIRCLE_HEX = "Circle Hexagonal";
    public static final String PLANAR_CIRCLE_GRID = "Circle Regular Grid";
}
