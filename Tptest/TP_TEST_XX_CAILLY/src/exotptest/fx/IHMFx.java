package exotptest.fx;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IHMFx extends Application{
    
    public IHMFx(){}
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(new FileInputStream(new File("fxml/window.fxml")));
        
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Quizz table des types");
        
        primaryStage.show();
    }
}
