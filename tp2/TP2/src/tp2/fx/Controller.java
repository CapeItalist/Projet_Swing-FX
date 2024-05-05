package tp2.fx;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import jeu_image.RuleManager;

public class Controller {
    private RuleManager manager;
    private ImageView[] images;
    
    public Controller(){
        manager=new RuleManager(3, 3);
    }
    
    @FXML
    GridPane grid;
    
    @FXML
    public void initialize(){
        images = new ImageView[9];
        for(int i=0; i<9; ++i){
            int column=i%3;
            int row=i/3;
            
            ImageView image = new ImageView(new File("images/Diablo_IV_0"+manager.getImagesValues()[column][row]+".jpg").toURI().toString());
            image.fitWidthProperty().bind(grid.widthProperty().divide(3));
            image.fitHeightProperty().bind(grid.heightProperty().divide(3));
            
            image.setOnMouseClicked(e->{
                int c = grid.getColumnIndex((Node)e.getSource());
                int r = grid.getRowIndex((Node)e.getSource());
                
                try{
                    manager.play(c, r);
                    updateGrid();
                }
                catch(Exception ex){
                    System.out.println("Ce coup n'est pas valide.");
                }
            });
            
            images[i]=image;
            grid.add(image, column, row);
        }
    }
    
    private void updateGrid(){
        for(int i=0; i<images.length; ++i){
            int column=i%3;
            int row=i/3;
            images[i].setImage(new Image(new File("images/Diablo_IV_0"+manager.getImagesValues()[column][row]+".jpg").toURI().toString()));
        }
    }
}
