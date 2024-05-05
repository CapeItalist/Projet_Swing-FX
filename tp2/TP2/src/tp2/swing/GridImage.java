package tp2.swing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jeu_image.RuleManager;

public class GridImage extends JLabelImage{
    private final Integer index;
    
    public GridImage(Integer index){
        super("images", "Diablo_IV_0"+(index+1), ".jpg");
        
        this.index=index;
    }
    
    public int getColumn(){
        return index%3;
    }
    
    public int getRow(){
        return index/3;
    }
    
    public void setIndex(Integer index){
        if(index!=null)
            setPath("images", "Diablo_IV_0"+(index), ".jpg");
        else
            setPath("images", null, ".jpg");
    }
}
