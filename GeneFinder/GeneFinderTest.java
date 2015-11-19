
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit Test for GeneFinder. 
 * Check if genes are correctly recognized in input string
 * 
 * @author Pawel Daniluk 
 * @version 2015-10-22
 */
public class GeneFinderTest {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    
   GeneFinder fg;
     
    @Before
    public void setUp() {
        fg = new GeneFinder();
    }

    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }




    @Test
    public void testFindGene()
    {
        String genome = "";
        String expected = "";
        
        // Test 1
        genome = "xxx";
        expected = "";
        assertEquals(expected, fg.findGene(genome));
        
        // Test 2
        genome = "“AATGCTAGTTTAAATCTGA”";
        expected = "ATGCTAGTTTAAATCTGA";
        assertEquals(expected, fg.findGene(genome));
        
        // Test 3
        genome = "ataaactatgttttaaatgt";
        expected = "atgttttaa".toUpperCase();
        
        assertEquals(expected, fg.findGene(genome));       
        
        
        // Test 4
        genome = "acatgataacctaag";
        expected = "";
        assertEquals(expected, fg.findGene(genome));
        
        // Custom tests
        
        // some gene
        genome = "xxxATGxxxyyyTAGyyy";
        expected = "ATGXXXYYYTAG";
        assertEquals(expected, fg.findGene(genome));
        
        // No start tag
        genome = "xxxyyyTAG";
        expected = "";
        assertEquals(expected, fg.findGene(genome));
        
        // Not a gene
        genome = "ATGxxyyyTAG";
        expected = "";
        assertEquals(expected, fg.findGene(genome));
        
        // TGA tag
        genome = "asdfATGxxxyyyTGAssassdfaTAG";
        expected = "ATGxxxyyyTGA".toUpperCase();
        assertEquals(expected, fg.findGene(genome));
        
        // TAA tag
        genome = "asdfATGxxxyyyzzzTAAssassTGAsdfdfaTAG";
        expected = "ATGxxxyyyzzzTAA".toUpperCase();
        assertEquals(expected, fg.findGene(genome));
    }
}



