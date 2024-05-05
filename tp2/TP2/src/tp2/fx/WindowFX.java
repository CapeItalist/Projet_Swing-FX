package tp2.fx;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowFX extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(new FileInputStream("fxml/window.fxml"));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Image FX");
        primaryStage.show();
    }
}
