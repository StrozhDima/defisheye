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
public class App {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        CalibView calibView = new CalibView(view, true);
        Controller controller = new Controller(model, view, calibView);
        controller.initController();
    }
}