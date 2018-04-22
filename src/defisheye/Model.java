/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defisheye;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

/**
 *
 * @author Dzmitry
 */
public class Model extends JFileChooser {

    public Model() {
    }

    String getTypeOfFile(File file) {
        String type = null;
        Matcher m = Pattern.compile(".*/.*?(\\w*)").matcher(file.toString());
        if (m.matches()) {
            type = m.group(1);
        }
        return type;
    }
}
