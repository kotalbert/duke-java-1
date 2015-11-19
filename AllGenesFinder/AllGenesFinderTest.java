
import static org.junit.Assert.*;
import org.junit.Test;
import edu.duke.StorageResource;
import edu.duke.FileResource;

/**
 * Unit test for AllGenesFinder class.
 * 
 * @author Pawel Daniluk 
 * @version 2015-10-24
   */
public class AllGenesFinderTest {
    AllGenesFinder agf = new AllGenesFinder();

    @Test
    public void testFindStartIndex() {
        /*public static void assertEquals(java.lang.String message,
                                java.lang.Object expected,
                                java.lang.Object actual)*/
        String dna = "";
                                
        //test 1: find 1 start index
        dna = "xxxATGyyy";
        assertEquals(3,agf.findStartIndex(dna, 0));
        
        //test 2: find 2 start index
        dna = "xxxATGyyyATGxxx";
        assertEquals(9,agf.findStartIndex(dna, 6));
        
        //test 3: no start index
         dna = "xxxyyyzzz";
         assertEquals(-1, agf.findStartIndex(dna, 0));
    
        
    }
    
    @Test
    public void testFindStopIndex() {
        
        String dna = "";
        int start = 0;
        int expected = 0;
        int actual = 0;
        
        // test 1: find TAG codon
        dna = "xxxATGyyyzzzTAG";
        start = agf.findStartIndex(dna, 0);
        actual = agf.findStopIndex(dna, start);
        expected = 12;
        
        assertEquals(expected, actual);
        
        // test 2: find TGA codon
        dna = "xxxATGyyyzzzTGA";
        start = agf.findStartIndex(dna, 0);
        actual = agf.findStopIndex(dna, start);
        expected = 12;
        
        assertEquals(expected, actual);
        
        // test 3: find TAA codon
        dna = "xxxATGyyyzzzTAA";
        start = agf.findStartIndex(dna, 0);
        actual = agf.findStopIndex(dna, start);
        expected = 12;
        
        assertEquals(expected, actual);
        
        //test 4: find correct gene
        dna = "xxxATGyyyzTAGzzTAA";
        start = agf.findStartIndex(dna, 0);
        actual = agf.findStopIndex(dna, start);
        expected = 15;
        
        assertEquals(expected, actual);
        
        // test 5: end index for 1st example dna strand
        dna = "ATGAAATGAAAA";
        start = agf.findStartIndex(dna, 0);
        actual = agf.findStopIndex(dna, start);
        expected = 6;
        
        assertEquals(expected, actual);       
        
    }
    
    private <T> void pl(T t) {
        
        System.out.println(t);
    }
    
    @Test 
    public void testPrintAll() {
    
        String dna = "";
        
        //test 1
        pl("Test 1");
        dna = "ATGAAATGAAAA";
        pl(dna);
        agf.printAll(dna);
        
        // test 2
        pl("Test 2");
        dna = "ccatgccctaataaatgtctgtaatgtaga";
        pl(dna);
        agf.printAll(dna);
        
        // test 3
        pl("Test 3");
        dna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        pl(dna);
        agf.printAll(dna);
        
        //test 4: custom
        pl("Test 4");
        dna = "zzATGzzzTAGyyTAGzzATGzTAGzzTAA";
        pl(dna);
        agf.printAll(dna);
        
    }
    
    @Test
    public void testStoreAll() {
        String dna = "";
        StorageResource store;
        
        //test 1
        pl("Test 1");
        dna = "ATGAAATGAAAA";
        pl(dna);
        store = agf.storeAll(dna);
        for (String gene : store.data()) {
        
            pl(gene);
        }
        
        // test 2
        pl("Test 2");
        dna = "ccatgccctaataaatgtctgtaatgtaga";
        pl(dna);
        store = agf.storeAll(dna);
        for (String gene : store.data()) {
        
            pl(gene);
        }
        
        
        // test 3
        pl("Test 3");
        dna = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        pl(dna);
        store = agf.storeAll(dna);
        for (String gene : store.data()) {
        
            pl(gene);
        }
       
        //test 4: custom
        pl("Test 4");
        dna = "zzATGzzzTAGyyTAGzzATGzTAGzzTAA";
        pl(dna);
        store = agf.storeAll(dna);
        for (String gene : store.data()) {
        
            pl(gene);
        }
        
    }
    
    @Test
    /**
     * Test the storeAll() with large file: brca1line.fa.
     * Test printGenes() on a result of storeAll().
     */
    public void testStorageFinder() {
        
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        StorageResource store = agf.storeAll(fr.asString());
        agf.printGenes(store);
        
        
    }
    
    @Test
    public void testCgRatio() {
        
        String dna = "";
        
        //test 1
        dna = "xxxaaasss";
        assertEquals(-1.0d, agf.cgRatio(dna), 0);  
        
        //test 2
        dna = "cccaaasss";
        assertEquals(-1.0d, agf.cgRatio(dna), 0);  
        
        //test 3
        dna = "cccaaaggg";
        assertEquals(1.0d, agf.cgRatio(dna), 0);  
        
        //test 4
        dna = "cccaaagggzzzggg";
        assertEquals(0.5d, agf.cgRatio(dna), 0);  
    
    }
    
    @Test
    public void testCountCtg() {
    
        // quiz 
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        pl("CTG count: " + agf.countCtg(dna));
        
       
        
        // test 1
        //dna = "CTGzzCTGyyyCTG";
        //assertEquals(3, agf.countCtg(dna));
        
        // test 2
        //dna = "xxxyyyzzztttqqq";
        //assertEquals(0, agf.countCtg(dna));
    }
    
    @Test
    public void testGetLongestGeneLength() {
        
        // quiz
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource store = agf.storeAll(fr.asString());
        pl("The longest gene: " + agf.getLongestGeneLength(store)); 
        
        //test 1
        //sr = agf.storeAll(dna);
        //assertEquals(-1, agf.getLongestGeneLength(sr));
        
        //test 2
        //dna = "zzATGzzzTAGyyTAGzzATGzTAGzzTAA";
        //sr = agf.storeAll(dna);
        //assertEquals(12, agf.getLongestGeneLength(sr));
        
    }
    
}
