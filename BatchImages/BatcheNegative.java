
/**
 * Program for converting selected images to negative.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-21
 */

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

public class BatcheNegative {

    /**Select images to be converted to negative and saved in the current directory.*/
    public void selectImages() {
    
        DirectoryResource dir = new DirectoryResource();
        
        for (File f : dir.selectedFiles()) {
            ImageResource newImage = convertToNegative(new ImageResource(f));
            newImage.setFileName("negative-" + f.getName());
            newImage.draw();
            newImage.save();
        } 
        
        
    }
    //TODO: convert files into greyscale
    /**
     * Convert a picture to negative and return new converted picture.
     * @param   inImage image that will be processed
     * @return  new processed image
     */
    private ImageResource convertToNegative(ImageResource inImage) {
    
        // create new blank image
        ImageResource newImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        // for each pixel calculate oposite value of each color
        // and put the value on the new image
        for (Pixel px : inImage.pixels()) {
        
            // Set pixels on new image to match mean RBG values
            Pixel modifiedPixel = newImage.getPixel(px.getX(), px.getY());
            // put negative colors on new image
            
            modifiedPixel.setRed(negativeColorValue(px.getRed()));
            modifiedPixel.setBlue(negativeColorValue(px.getBlue()));
            modifiedPixel.setGreen(negativeColorValue(px.getGreen()));
        }
        
        // return new image
        return newImage;
        
    }
    
    /**
     * Calculate negative value for a given color code: negative = 255 - positive.
     * @param   colorValue  integer representing given color value (0-255)
     * @return  value of negative color
     */
   private int negativeColorValue(int colorValue) {
       final int MAX_RBG = 255;
       return MAX_RBG - colorValue;
    } 
    
}











