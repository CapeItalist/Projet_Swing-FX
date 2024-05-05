package tp2.swing;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class JLabelImage extends JLabel {

    private String imageDirectory;
    private String imagePrefix;
    private String imageSuffix;
    
    private BufferedImage image;
    
    public JLabelImage(String directory, String prefix, String suffix) {
        imageDirectory = directory;
        imagePrefix = prefix;
        imageSuffix = suffix;
        
        load();
    }
    
    private void load()  {
        if(imagePrefix==null){
            image=null;
            return;
        }
        
	String fileName=imageDirectory;
        fileName+=File.separatorChar;
        fileName+=imagePrefix;
        fileName+=imageSuffix;
	
	var file=new File(fileName);

	if(! file.isFile()) {
	    System.err.println("\""+fileName+"\" is not a valid file");
            image=null;
        } else {
            try {
                image=ImageIO.read(file);
            } catch(java.io.IOException ioe) {
                System.err.println("Problem during \""+fileName+"\" parsing");
            }
        }
    }
    
    public void setPath(String directory, String prefix, String suffix){
        imageDirectory = directory;
        imagePrefix = prefix;
        imageSuffix = suffix;
        
        load();
        repaint();
    }
    
    @Override
    public void paint(java.awt.Graphics g) {
	super.paint(g);

	var g2=(java.awt.Graphics2D)g;

	int width  = (int)getSize().getWidth();
	int height = (int)getSize().getHeight();

	if(image!=null) {
            g2.drawImage(image, 0, 0, width, height, null);
	} else {
            g2.fillRect(0, 0, width, height);
            g2.setColor(Color.WHITE);
            g2.drawRect(0, 0, width, height);
            g2.setColor(Color.BLACK);
	}
    }
}
