package tp1.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tp1.Calculatrice_Core;

public class Controller {
    private Calculatrice_Core core;
    
    public Controller(){
        core = new Calculatrice_Core();
    }
    
    @FXML
    Label screen;
    @FXML
    Button digitButton0;
    @FXML
    Button digitButton1;
    @FXML
    Button digitButton2;
    @FXML
    Button digitButton3;
    @FXML
    Button digitButton4;
    @FXML
    Button digitButton5;
    @FXML
    Button digitButton6;
    @FXML
    Button digitButton7;
    @FXML
    Button digitButton8;
    @FXML
    Button digitButton9;
    @FXML
    Button addButton;
    @FXML
    Button minusButton;
    @FXML
    Button productButton;
    @FXML
    Button divideButton;
    @FXML
    Button equalButton;
    
    private void digitClick(int digit){
        core.add_number((""+digit).charAt(0));
        screen.setText(core.getCurrentValue().toString());
    }
    
    private void operationClick(char operation){
        core.add_symbol(operation);
        screen.setText(core.getCurrentValue().toString());
    }
    
    @FXML
    public void initialize(){
        digitButton0.setOnAction(e->{digitClick(0);});
        digitButton1.setOnAction(e->{digitClick(1);});
        digitButton2.setOnAction(e->{digitClick(2);});
        digitButton3.setOnAction(e->{digitClick(3);});
        digitButton4.setOnAction(e->{digitClick(4);});
        digitButton5.setOnAction(e->{digitClick(5);});
        digitButton6.setOnAction(e->{digitClick(6);});
        digitButton7.setOnAction(e->{digitClick(7);});
        digitButton8.setOnAction(e->{digitClick(8);});
        digitButton9.setOnAction(e->{digitClick(9);});
        
        addButton.setOnAction(e->{operationClick('+');});
        minusButton.setOnAction(e->{operationClick('-');});
        productButton.setOnAction(e->{operationClick('*');});
        divideButton.setOnAction(e->{operationClick('/');});
        equalButton.setOnAction(e->{operationClick('=');});
    }
}
