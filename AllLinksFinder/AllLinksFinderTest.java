
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Testing AllLinksFinder class.
 * 
 * @author Pwel Daniluk
 * @version 2015-10-25
 */
public class AllLinksFinderTest {
    
    @Test 
    public void testAllLinksFinder() {
        
        AllLinksFinder alf = new AllLinksFinder("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
    
        alf.printAll();
        alf.printUrlCount();
        alf.printSecuredLinksCount();
        alf.countLinksWithCom();
        alf.countLinksEndingWithCom();
        alf.countAllDots();
    }
}
