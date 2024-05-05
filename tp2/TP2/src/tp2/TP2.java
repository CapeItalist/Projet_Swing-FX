package tp2;

import javafx.application.Application;
import tp2.fx.WindowFX;
import tp2.swing.WindowSwing;

public class TP2 {
    public static void main(String[] args) {
        //new WindowSwing().show();
        Application.launch(WindowFX.class, args);
    }
}
