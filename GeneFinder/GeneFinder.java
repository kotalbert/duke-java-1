
/**
 * Class for finding a gene sequence in a given genome string.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-22
 */

public class GeneFinder {

    /**
     * Find a gene sequence in given genome String.
     * 
     * @param   genomeString    string in which to look for a gene sequence
     * @return  string with found gene or "" (empty string) if no gene is found
     */
    String findGene(String genomeString) {
    
        // string constants for searching genes 
        final String G = genomeString.toUpperCase();
        final String START_ATG = "ATG";
        final String END_TGA = "TGA";
        final String END_TAG = "TAG";
        final String END_TAA = "TAA";
        
        //find start codon, end function if not found
        int start = G.indexOf(START_ATG);
        if (start == -1) return "";
        
        //find end TAG codon and return if found correct gene
        int end = G.indexOf(END_TAG, start+3);
        if(isGene(start,end)) return getGene(start, end, G);
        
        // chech TGA codon
        end = G.indexOf(END_TGA, start+3);
         if(isGene(start,end)) return getGene(start, end, G);
        
        // check TAA codon
         end = G.indexOf(END_TAA, start+3);
         if(isGene(start,end)) return getGene(start, end, G);
        
        // return empty string if not found
        return "";
    }
    
    /**
     * Check if start if number of characters between start and end codon represents a gene
     * @param   startIndex  index of start codon
     * @param   endIndex    index of end codon
     * @return  True if number of characters is 3N
     *          False otherwise
     */
    private boolean isGene(int startIndex, int endIndex) {
    
        if ((endIndex - startIndex+3)%3==0) return true;
        
        return false; 
    
    }
    
    /**
     * Return string representing correct gene from a genome strand
     * @param   start   index of start codon
     * @param   end     index of end codon
     * @return  string representing correct gene
     */
    private String getGene(int start, int end, String genome) {
        return genome.substring(start, end+3);
    }
}
