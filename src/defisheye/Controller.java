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
public class Controller {

    private final Model model;
    private final View view;
    private final CalibView calibView;
    
    public Controller(Model m, View v, CalibView cv) {
        model = m;
        view = v;
        calibView = cv;
        this.view.initView();
    }

    void initController() {
        
    }
}