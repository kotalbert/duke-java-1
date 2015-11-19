
/**
 * Program for converting selected images into greyscale.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-20
 */

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import java.io.File;

public class BatchGreyscale {

    
    //TODO: select files
   /**Select images to be converted to greyscale and saved in the current directory.*/
    public void selectImages() {
    
        DirectoryResource dir = new DirectoryResource();
        
        for (File f : dir.selectedFiles()) {
            ImageResource newImage = convertToGreyscale(new ImageResource(f));
            newImage.setFileName("grey-" + f.getName());
            newImage.draw();
            newImage.save();
        } 
        
        
    }
    //TODO: convert files into greyscale
    /**
     * Convert a picture to a greyscale and return new converted picture
     * @param   inImage image that will be processed
     * @return  new processed image
     */
    private ImageResource convertToGreyscale(ImageResource inImage) {
    
        // create new blank image
        ImageResource newImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        // for each pixel calculate avg RBG value
        // and put the avg on the new image
        for (Pixel px : inImage.pixels()) {
        
            int rbgMean = getRBGMean(px);
            // Set pixels on new image to match mean RBG values
            Pixel modifiedPixel = newImage.getPixel(px.getX(), px.getY());
            // put grey pixel to new image
            modifiedPixel.setRed(rbgMean);
            modifiedPixel.setBlue(rbgMean);
            modifiedPixel.setGreen(rbgMean);
        }
        
        // return new image
        return newImage;
        
    }
    
    /**
     * 
     * Return oposite color value for a given color value.
     * @param   px  Pixel for which average RBG value is to be calculated
     * @return  averege RBG value for greyscale image
       */
    private int getRBGMean(Pixel px) {
        return (px.getRed()+ px.getBlue() + px.getGreen())/3;
    }
    
    
}











