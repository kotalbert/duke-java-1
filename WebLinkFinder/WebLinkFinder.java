
/**
 * Class for finding links to youtube on a given web page
 * 
 * @author Pawel Daniluk 
 * @version 2015-10-22
 */
import edu.duke.URLResource;

public class WebLinkFinder {
    
    private URLResource urlRes = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

    /**
     * Go trought page content and call a function checking each word and printing YT links
     */
    public void printVideoUrls() {
    
        for (String word : urlRes.words()) {
            
            printVideoURL(word);
            
        }
    
    }
    
    /**
     * Find a proper YT url and print it to console: 
     * containing youtube.com surounded by souble quotes
     * @param   s   a word from input string, with possible YT url in it
     */
    private void printVideoURL(String s) {
    
        final String S_LOW_CASE = s.toLowerCase();
        
        // TODO: make sure to return correct url (case sensitive)
        
        // check if s contains youtube.com substring, end function if not found
        int ytcomIndex = S_LOW_CASE.indexOf("youtube.com");
        if (ytcomIndex == -1) return;
//         System.out.print(s+"\t");
        
        // check if youtube.com is surounded with double quotes
       int firstQuoteIndex = S_LOW_CASE.indexOf("\"");
//        System.out.print("First quote:" + firstQuoteIndex + "\t");
       if (firstQuoteIndex == -1) return;
       int lastQuoteIndex = S_LOW_CASE.lastIndexOf("\"");
//        System.out.print("Last quote:" + lastQuoteIndex + "\n");
       if (lastQuoteIndex == -1) return;
//        
       String outString = s.substring(firstQuoteIndex+1, lastQuoteIndex);
       System.out.println(outString);
       
        
        
    }
    
}
