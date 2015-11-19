
import static org.junit.Assert.*;
import org.junit.Test;
import edu.duke.FileResource;

/**
 * Write a description of BabyNamesTest here.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-29
 */
public class BabyNamesTest {
  
    BabyNames bn = new BabyNames();
    
    @Test
    public void testFile() {
    
        FileResource fr = new FileResource();
        bn.totalBirths(fr);
        
    }
    
    @Test
    public void testGetRank() {
    
        int rank1 = bn.getRank(2012, "Emma", "F");
        assertEquals(2, rank1);
        
        int rank2 = bn.getRank(2012, "Olivia", "F");
        assertEquals(4, rank2);
        
        int rank3 = bn.getRank(2012, "Olivia", "M");
        assertEquals(-1, rank3);
        
        int rank4 = bn.getRank(2012, "XXX", "F");
        assertEquals(-1, rank4);
        
        int rank5 = bn.getRank(2012, "Ethan", "M");
        assertEquals(3, rank5);
    }
    
    @Test
    public void testGetName() {
    
        String name1 = bn.getName(2012, 2, "F");
        assertEquals("Emma", name1);
        
        String name2 = bn.getName(2012, 1200, "M");
        assertEquals("NO NAME", name2);
        
        String name3 = bn.getName(2012, 3, "M");
        assertEquals("Ethan", name3);
        
    }
    
    @Test
    public void testWhatIsNameInYear() {
        
        String name1 = bn.whatIsNameInYear("Isabella", 2012, 2014, "F");
        assertEquals("Sophia", name1);
        
        String name2 = bn.whatIsNameInYear("Isabella", 2014, 2012, "F");
        assertEquals("Olivia", name2);
        
        String name3 = bn.whatIsNameInYear("Jacob", 2014, 2012, "M");
        assertEquals("Noah", name3);
    }
    
    @Test
    public void testYearOfHighestRank() {
    
        int year = bn.yearOfHighestRank("Mason", "M");
        System.out.println("Year of HighestRank: " + year);
    }
    
    @Test
    public void testGetAverageRank() {
        
        double rank1 = bn.getAverageRank("Jacob", "M");
        assertEquals(rank1, 2.66d, 0.01d);
        
        double rank2 = bn.getAverageRank("XXX", "M");
        assertEquals(rank2, -1.0d, 0.01d);
    }
    
    @Test
    public void testGetTotalBirthsRankedHigher() {
    
        int avgRank1 = bn.getTotalBirthsRankedHigher(2012, "Ethan", "M");
        assertEquals(15, avgRank1);
        
        int avgRank2 = bn.getTotalBirthsRankedHigher(2012, "Isabella", "F");
        assertEquals(19, avgRank2);
        
    }
}
