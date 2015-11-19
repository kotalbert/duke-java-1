
/**
 * Finding, storing and analyzing all links on a webpage.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-25
 */

import edu.duke.StorageResource;
import edu.duke.URLResource;

public class AllLinksFinder {
    
    private String url;
    private StorageResource store;
    
    /**
     * Create all links finder from a url string passed as argument.
     * @param   url url string to be processed
     */
    public AllLinksFinder(String url) {
    
        this.url = url;
        storeAll();
        
    }

    private <T> void pl(T t) {System.out.println(t);}
    
    /**
     * Helper function to check if given word is a link.
     */
    private boolean isLink(String word) {
        
        final String WORD = word.toUpperCase();
        
        if (WORD.indexOf("HREF=\"HTTP") == -1) return false;
        return true;
    
    }
    
    /**
     * Helper function to extract valid link from a word and store it.
     * @param   word    string containing HTTP substring
     */
    private void storeLink(String word){
        
        // find firs and last double quote
        int openingQ = word.indexOf("\"");
        int closingQ = word.indexOf("\"", openingQ+1);
        if (closingQ == -1) closingQ = word.length()-1;
        
        // extract link substring
        try {
        String link = word.substring(openingQ+1, closingQ);
        // store
        store.add(link); } catch(Exception e) {
        
        pl(e.getStackTrace());
        pl(">"+word+"<");
        pl("********************");
        }
        
    
    }
    
    /**
     * Print all stored links.
     */
    public void printAll() {
    
        for (String link : store.data()) {
        
            pl(link);
        }
    }
    
    /**
     * Read webpage under url and store all links found.
     * 
     */
    private void storeAll() {
    
        store = new StorageResource();
        URLResource ur = new URLResource(url);
        
        for (String word : ur.words()) {
        
            if(isLink(word)) storeLink(word);
        }
        
    }
    
    public void printUrlCount() {
    
        pl("Number of found URLs: "+store.size());
    }
    
    public void printSecuredLinksCount() {
    
        int count = 0;
        for (String link : store.data()) {
            String LINK = link.toUpperCase();
            if (LINK.indexOf("HTTPS") != -1) count++;
        }
        
        pl("Number of secured links found: "+count);
    }
    
    public void countLinksWithCom() {
        
        int count = 0;
        for (String link : store.data()) {
            String LINK = link.toUpperCase();
            if (LINK.indexOf(".COM") != -1) count++;
        }
        
        pl("Number of links with .COM found: "+count);
        
    }
    
    /**
     * Print the number of links that end with “.com” or “.com/”
     */
    public void countLinksEndingWithCom() {
        int count = 0;
        for (String link : store.data()) {
            String LINK = link.toUpperCase();
        
            if (LINK.lastIndexOf(".COM") <= 5) count++;
        
        }
    
        pl("Number of links ending with .COM found: "+count);
    }

    public void countAllDots() {
        int dotCount = 0;
        
        for (String link : store.data()) {
        
            for (char ch : link.toCharArray()) {
                if (ch == '.') dotCount++;
            }
        }
    
        pl("Number of dots: "+dotCount);
        
    }

}
