

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;

/**
 * Testing of CSVExportsData.
 * 
 * @author Pawel Daniluk 
 * @version 2015-10-25
 */
public class CSVExportsDataTest {
    
    private CSVParser parser;
    private FileResource fr = new FileResource("exports/exportdata.csv");
    private CSVExportsData csvED = new CSVExportsData();

    private void reset() {
        parser = fr.getCSVParser();
    }

    @Test
    public void testCountryInfo() {
        reset();
        csvED.countryInfo(parser, "Nauru");
        
        reset();
        csvED.countryInfo(parser, "Germany");
        
        reset();
        csvED.countryInfo(parser, "Madagascar");
        
        reset();
        csvED.countryInfo(parser, "Namibia");
        reset();
        csvED.countryInfo(parser, "United States");
        
        reset();
        csvED.countryInfo(parser, "XXX");
    }
    
    @Test 
    public void testListExportersTwoProducts() {
        
        reset();
        csvED.listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    @Test
    public void testNumberOfExporters() {
        reset();
        csvED.numberOfExporters(parser, "diamonds");
        
        reset();
        csvED.numberOfExporters(parser, "gold");
        
    }
    
    @Test
    public void testBigExporters() {
        reset();
        csvED.bigExporters(parser, "$999,999,999,999");
        
    
    }
}
