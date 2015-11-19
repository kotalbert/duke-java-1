
import static org.junit.Assert.*;
import org.junit.Test;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord; 
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;

/**
 * Testing CSVWeatherReader
 * 
 * @author Pawel Daniluk
 * @version 2015-10-26
 */
public class CSVWeatherReaderTest {
    CSVWeatherReader wr = new CSVWeatherReader();
    CSVParser parser;

    private <T> void p(T t){System.out.print(t);}
    private <T> void pl(T t) {System.out.println(t);}
    
    private void getFile() {
        FileResource fr = new FileResource();
        parser = fr.getCSVParser();
        
    }
    
    
    @Test
    public void testColdestHourInFile() {
        
        getFile();
        CSVRecord coldest = wr.coldestHourInFile(parser);
        // print record:
        p("Lowest temperature in selected file: ");
        p(coldest.get(wr.TEMP_NAME));
        pl(" recorded: " + coldest.get(wr.UTC_DATE));
        
        
    }
    
    @Test
    public void testFileWithColdestTemperature() {
        
        wr.fileWithColdestTemperature();

    }
    
    @Test
    public void testLowestHumidityInFile() {
    
        getFile();
        CSVRecord lowest = wr.lowestHumidityInFile(parser);
        // print record:
        p("Lowest humidity in selected file: ");
        p(lowest.get(wr.HUM_NAME));
        pl(" recorded: " + lowest.get(wr.UTC_DATE));
    
    }
    
    @Test
    public void testLowestHumidityInManyFiles(){
        
        wr.lowestHumidityInManyFiles();
        
    }
    
    @Test
    public void testAverageTemperatureWithHighHumidityInFile() {
        getFile();
        int value = 80;
        wr.averageTemperatureWithHighHumidityInFile(parser, value);
    
    }
}
