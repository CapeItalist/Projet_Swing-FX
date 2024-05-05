package exotptest.fx;

import exotptest.PokemonTypeCore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Controller {
    private static final String RED = "ff0000";
    private static final String GREEN = "00aa00";
    
    private int time;
    private Timeline timeline;
    private PokemonTypeCore core;
    
    public Controller(){
        core=new PokemonTypeCore();
    }
    
    @FXML
    private Label type1;
    @FXML
    private Label type2;
    @FXML
    private Label score;
    @FXML
    private Label timer;
    @FXML
    private Button inefficace;
    @FXML
    private Button pasTresEfficace;
    @FXML
    private Button neutre;
    @FXML
    private Button superEfficace;
    
    public void update(){
        timer.setText(""+time+" s");
        type1.setText(core.getType1());
        type2.setText(core.getType2());
        
        score.setText("Score : "+core.getScore());
    }
    
    public void tentative(String relation){
        if(time>3)
            time=3;
        
        core.tentative(relation);
        
        setButtonsColor(RED);
        
        switch (core.getGoodResponse()) {
            case "INEFFICACE" -> setButtonColor(inefficace, GREEN);
            case "PAS_TRES_EFFICACE" -> setButtonColor(pasTresEfficace, GREEN);
            case "NEUTRE" -> setButtonColor(neutre, GREEN);
            case "SUPER_EFFICACE" -> setButtonColor(superEfficace, GREEN);
        }
    }
    
    public void setButtonsColor(String color){
        String css = color.equals("default") ? "":"-fx-background-color: #"+color+";";
        
        inefficace.setStyle(css);
        pasTresEfficace.setStyle(css);
        neutre.setStyle(css);
        superEfficace.setStyle(css);
    }
    
    public void setButtonColor(Button button, String color){
        String css = "-fx-background-color: #"+color+";";
        
        button.setStyle(css);
    }
    
    public void initialize(){
        time=15;
        
        update();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e->{
            if(time==0){
                time=15;
                core.generate();
                
                setButtonsColor("default");
            }
            else{
                time--;
            }
            update();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    @FXML
    public void inefficaceClick(){
        tentative("INEFFICACE");
    }
    
    @FXML
    public void pasTresEfficaceClick(){
        tentative("PAS_TRES_EFFICACE");
    }
    
    @FXML
    public void neutreClick(){
        tentative("NEUTRE");
    }
    
    @FXML
    public void superEfficaceClick(){
        tentative("SUPER_EFFICACE");
    }
}
