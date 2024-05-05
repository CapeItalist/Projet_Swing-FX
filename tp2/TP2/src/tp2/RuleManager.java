package jeu_image;

import java.util.ArrayList;

/**
 * Manager of the image game rules
 * 
 * @author Jean-Charles BOISSON
 * @version 1.1 2024 03
 */
public class RuleManager {
      
    /** line   of the empty case */
    private int empty_row;
    /** column of the empty case */
    private int empty_column;

    /** the image distribution */
    private final Integer[][] images;
    /** number of the not used image */
    private final Integer missing_image;

    /**
     * 
     * Constructor of the rule manager
     * 
     * @param row_nb number of rows of the grid
     * @param column_nb number of columns of the grid
     */
    public RuleManager(int row_nb, int column_nb) {
        
        images=new Integer[row_nb][column_nb];
        
        shuffle();

        empty_row    = row_nb    - 1;
        empty_column = column_nb - 1;

        missing_image=images[empty_row][empty_column];
        
        images[empty_row][empty_column]=null;
    }

    /**
     * Tool procedure which randomly shuffle the images
     */
    private void shuffle() {
        
        ArrayList<Integer> list = new java.util.ArrayList<>();

        int image_nb=images.length*images[0].length;

        while(list.size()!=image_nb) {
            int choice = (int)((Math.random()*image_nb)+1);
            if(!list.contains(choice)) {
                list.add(choice);
            }
        }

        // Real shuffle
        int i=0;
        int j=0;
        for(int number : list) {
            images[i][j] = number;
            i=(i+1)%images.length;
            if(i==0) j+=1;
        }
        
        // No shuffle (for testing purpose)
        /*int number=1;
        for(int i=0;i<images.length;i++) {
            for(int j=0;j<images[i].length;j++) {
                images[i][j]=number;
                number++;
            }
        }*/
    }

    /**
     * Classical override of the toString function
     * 
     * @return A text description of the current state of the grid
     */
    @Override
    public String toString() {
        String state= "";
        for(var line : images) {
            for(var value : line)
                state+=value+" ";
            state+="\n";
        }
        System.out.println("Missing image is "+missing_image);
        return state;
    }

    /**
     * Function which applies the choice of the user if it is valid.
     * @warning This function can throw a RunTimeException
     * 
     * @param i Chosen row
     * @param j Chosen column
     * @return True if the game is finished, false otherwise.
     */
    public boolean play(int i, int j) {
        if(i==empty_row && j==empty_column) {
            throw new RuntimeException("The empty case is not playable");
        }

        if(i!=empty_row && j!=empty_column) {
            throw new RuntimeException("The chosen case has to be next to the empty one ("+empty_row+","+empty_column+")");
        }

        int index_empty, index_chosen;
        
        if(i==empty_row) {
            index_empty=empty_column;
            index_chosen=j;
        } else {
            index_empty=empty_row;
            index_chosen=i;
        }
        
        int distance = Math.max(index_empty,index_chosen)-Math.min(index_empty,index_chosen);

        if(distance > 1) {
            throw new RuntimeException("The distance between chosen and empty case is too high: "+distance);
        }

        images[empty_row][empty_column]=images[i][j];
        
        empty_row=i;
        empty_column=j;
        
        images[empty_row][empty_column]=null;

        return isFinished();
    }

    /**
     * Tool function which verifies if the game is finished or not (called by play function)
     * 
     * @return True if the game is finished, false otherwise.
     */
    private boolean isFinished() {
        int row    = (missing_image-1)/images.length;
        int column = (missing_image-1)%images.length;
        
        if( (empty_row!=row) || (empty_column!=column) )
            return false;
        
        int count = 1;
        boolean ok = true;
        int i=0;
        while(ok && i<images.length) {
            int j=0;
            while(ok && j<images[i].length) {
                if( (i!=empty_row) || (j!=empty_column) )
                    ok = (images[i][j]==count);
                count++;
                j++;
            }
            i++;
        }
      
        return ok;
    }
    
    /**
     * Function to get the current state of the game
     * 
     * @return The current configuration of the image game
     */
    public Integer[][] getImagesValues() {
        return images;
    }

}
