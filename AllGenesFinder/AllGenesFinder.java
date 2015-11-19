
/**
 * Finding all genes in a dna strand. 
 * 
 * @author Pawel Daniluik 
 * @version 2015-10-23
   */

import edu.duke.StorageResource;
  
public class AllGenesFinder {

    //gene start/end codons
    final String START_ATG = "ATG";
    final String END_TAG = "TAG";
    final String END_TGA = "TGA";
    final String END_TAA = "TAA";
    final String CODON_CTG = "CTG";
    
    /**
     * Find and print all genes in a dna strand.
     * @param   dna dna strand to be searched
     */
    public void printAll(String dna) {
        final String DNA = dna.toUpperCase();
        // loop whole dna strand
        int start = 0;
        int end = 0;
        while (true) {
            // find start codon
            start = findStartIndex(DNA, start);
            
            // find closest stop codon
            end = findStopIndex(DNA, start);
            
            // break if no start coson
            if (start == -1)  break;
            
            // check if not an EOF
            if (end < DNA.length() - 3) {
                    System.out.println(DNA.substring(start, end+3));
                    // repeat starting from end of found gene 
                    start = end + 3;
                }
            else {
                // gene not foud: restart search just after previous start codon 
                start += 3;
            }
 
        }
        
    }
    
    /**
     * Store all genes found in a dna strand and return a StorageResource with the result.
     * @param   dna dna strand to be searched and stored
     * @return  StorageResource object with found genes stored
     */
    public StorageResource storeAll(String dna) {
    
        StorageResource store = new StorageResource();
        final String DNA = dna.toUpperCase();
        // loop whole dna strand
        int start = 0;
        int end = 0;
        while (true) {
            // find start codon
            start = findStartIndex(DNA, start);
            
            // find closest stop codon
            end = findStopIndex(DNA, start);
            
            // break if no start or no end found
             if (start == -1)  break;
            
            // check if not an EOF
            if (end < DNA.length() - 3) {
                    store.add((DNA.substring(start, end+3)));
                    // repeat starting from end of found gene 
                    start = end + 3;
                }
            else {
                // gene not foud: restart search just after previous start codon 
                start += 3;
            }
     
            
            
        }
        return store;
    }
    
    
    /**
     * Find index of start codon, starting search from a given position in dna strand.
     * @param   dna dna strand to be searched (should be in uppercase)
     * @param   start   start index from which dna string should be searched
     * @return  index of start codon, from a start position
     */
    public int findStartIndex(String dna, int start) {
     
        return dna.indexOf(START_ATG, start);
        
    }
    
    /**
     * Helper function for checking if start and end indicies represents correct gene sequence.
     * 
     * @param   start   index of start codon
     * @param   end     index of end codon
     * @result  true if number of elements between start and end indices are multiply of 3
     *          false if end == - 1 or number of elements not a multiply of 3
     */
    private boolean isCorrectGene(int start, int end){
    
        if ((end == -1) || ((end - start)%3!=0)) return false;
        
        return true;
        
    }
    
    /**
     * Find stop codon of correct gene, starting search from a given position in dna strand.
     * @param   dna dna strand to be searched (should be in uppercase)
     * @param   start   start index from which dna string should be searched
     * @return  index of a stop codon of a correct gene. Should return dna.lenght() if
     *          no correct gene is found in dna strand
     */
    public int findStopIndex(String dna, int start) {
        
        //exclude start codon from search string
        start += 3;
        
        // for each codon type:
        //find stop codon and check if number of elements from start to codon index is multiply of 3
        int tagIndex = dna.indexOf(END_TAG, start);
        int tagStopIndex = dna.length();
        if  (isCorrectGene(start, tagIndex)) tagStopIndex = tagIndex;
        
        int tgaIndex = dna.indexOf(END_TGA, start);
        int tgaStopIndex = dna.length();
        if (isCorrectGene(start, tgaIndex)) tgaStopIndex = tgaIndex;
        
        int taaIndex = dna.indexOf(END_TAA, start);
        int taaStopIndex = dna.length();
        if (isCorrectGene(start, taaIndex)) taaStopIndex = taaIndex;
        
        return Math.min(tagStopIndex, Math.min(tgaStopIndex, taaStopIndex));
    }
    
    /**
     * Return C-G-ratio in a given dna strand: (count(C)+count(G))/dna.lenght().
     * 
     * @param   dna dna strand to be processed
     * @return  C-G-ratio in a given dna strand
     * 
     */
    public double cgRatio(String dna) {
    
        String DNA = dna.toUpperCase();
                
        int cgCount = 0;
        for (char c : DNA.toCharArray()) {
            if ((c == 'G') || (c == 'C')) cgCount++; 
            
        }
        
       return  (double) cgCount / dna.length();
        
    
    }
    
    private <T> void pl(T t) { System.out.println(t);}
    
    /**
     * Print the following information:
     * prints all the Strings that are longer than 60 characters
     * prints the number of Strings that are longer than 60 characters
     * prints the Strings whose C-G-ratio is higher than 0.35
     * prints the number of strings whose C-G-ratio is higher than 0.35
     * 
     * @param   sr  StorageResource containing genes
     */
    public void printGenes(StorageResource sr) {
        
        pl("Printing store contents.");
        for (String str : sr.data()) {
            
            pl(str);
        }
        
        pl("Total number of genes");
        pl(sr.size());
        
        pl("Genes > 60");
        int cnt = 0;
        for (String gene1 : sr.data()) {
        
            if (gene1.length() > 60) {
                cnt++;
                pl(cnt+":\t"+gene1); 
            }
            
        }
    
        pl("Genes > 60 chars count: " + cnt);
        
        pl("Genes C-G-ratio > 0.35");
        cnt = 0;
        for(String gene2 : sr.data()) {
            double cg = cgRatio(gene2);
            if (cg > 0.35) {
                
                cnt++;
                pl(cnt+":\tcg: "+cg+"\t"+gene2);
            }
        
        }
        
        pl("Genes with CG ratio > 0.35 count: " +cnt);
       

}
    
    /**
     * Return number of  CTG codon that appears in dna strand
     */
   public int countCtg(String dna) {
    
        final String DNA = dna.toUpperCase();
        int ctgCount = 0; 
        int start = 0;
        int ctgIndex = 0;
        
        while (true) {
            
            ctgIndex = DNA.indexOf(CODON_CTG, start);
            if (ctgIndex == -1) break;
            ctgCount++;
            start = ctgIndex + 3;
            
        
        }
    
        return ctgCount;
    
    }
    
    /**
     * Check the StorageResource and return lenght of the longest gene stored.
     */
    public int getLongestGeneLength(StorageResource sr) {
        int maxLenght = -1;
        for (String gene : sr.data()) {
            if (gene.length() > maxLenght) maxLenght = gene.length();
        }
    return maxLenght;
    }

}