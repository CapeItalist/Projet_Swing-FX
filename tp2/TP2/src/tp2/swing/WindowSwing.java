package tp2.swing;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jeu_image.RuleManager;

public class WindowSwing extends JFrame{
    private final JPanel grid;
    private final GridImage[] images;
    private final RuleManager manager;
    
    public WindowSwing(){
        setTitle("Image Swing");
        resize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        manager = new RuleManager(3, 3);
        grid = new JPanel(new GridLayout(3, 3));
        
        images = new GridImage[9];
        for(int i=0; i<images.length; ++i){
            images[i]=new GridImage(i);
            images[i].addMouseListener(new ClickListener());
            images[i].setIndex(manager.getImagesValues()[i%3][i/3]);
            
            grid.add(images[i]);
        }
        
        getContentPane().add(grid);
    }
    
    private void updateGrid(){
        for(int i=0; i<images.length; ++i){
            images[i].setIndex(manager.getImagesValues()[i%3][i/3]);
        }
    }
    
    class ClickListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            GridImage image = (GridImage) e.getComponent();
            try{
                manager.play(image.getColumn(), image.getRow());
                updateGrid();
            }
            catch(Exception ex){
                System.out.println("Attention ce coup n'est pas permis.");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
        
    }
}
