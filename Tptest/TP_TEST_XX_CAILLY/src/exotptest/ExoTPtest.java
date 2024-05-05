package exotptest;

import exotptest.fx.IHMFx;
import exotptest.swing.IHMSwing;
import javafx.application.Application;

public class ExoTPtest {
    public static void main(String[] args) {
        //Application.launch(IHMFx.class,new String[1]);
        IHMSwing window = new IHMSwing();
        window.show();
    }
}
