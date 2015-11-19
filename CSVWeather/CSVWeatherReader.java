import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord; 
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;

/**
 * Reading and agregating CSV weather data.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-26
 */
public class CSVWeatherReader {

    // constants: column names in CSV files
    public final String TEMP_NAME = "TemperatureF";
    public final String UTC_DATE = "DateUTC";
    public final String HUM_NAME = "Humidity";
    
    private <T> void p(T t){System.out.print(t);}
    private <T> void pl(T t) {System.out.println(t);}
    
    /**
     * Find a CSVRecord of CSV file with a coldest temperature.
     * @param   parser  CSVParser to be searched
     * @return  CSVRecord containing the coldest temperature in file
     */
    public CSVRecord coldestHourInFile(CSVParser parser){
        
       CSVRecord coldestSoFar = null;
       
        for (CSVRecord record : parser) {
        
            // skip bogus temperature values
            if (Double.parseDouble(record.get(TEMP_NAME)) == -9999.0d) continue; 
            
            if (coldestSoFar == null) coldestSoFar = record;
            else {
                
                double tempSoFar = Double.parseDouble(coldestSoFar.get(TEMP_NAME));
                double tempCurrent = Double.parseDouble(record.get(TEMP_NAME));
                
                if (tempCurrent < tempSoFar) coldestSoFar = record;
            
            }
            
        }
        return coldestSoFar;
    }
    
    /**
     * Find a name of the file with the coldest temperature from selected files.
     * @return  name of file with coldest temperature
     */
    public String fileWithColdestTemperature() {
    
        DirectoryResource dr = new DirectoryResource();
        
        File coldestFileSoFar = null;
        CSVRecord coldestRecordSoFar = null;
        double coldestTempSoFar = 0.0d;
        
        for (File file : dr.selectedFiles()) {
            
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            
            if (coldestRecordSoFar == null) {
               coldestFileSoFar = file;
               coldestRecordSoFar = coldestHourInFile(parser);
               coldestTempSoFar = Double.parseDouble(coldestRecordSoFar.get(TEMP_NAME));
            }
           else {
               CSVRecord coldestCurrent = coldestHourInFile(parser);
               double currentTemp = Double.parseDouble(coldestCurrent.get(TEMP_NAME));
               coldestTempSoFar = Double.parseDouble(coldestRecordSoFar.get(TEMP_NAME));
               
               if (currentTemp < coldestTempSoFar){
                
                   coldestFileSoFar = file;
                   coldestRecordSoFar = coldestCurrent;
                }
        
            }
        }
        
        pl("Coldest file: " + coldestFileSoFar.getName());
        pl("Coldest temperature in file: " + coldestRecordSoFar.get(TEMP_NAME));
        
        // Print temperatures in file
        pl("Temperatures in file:");
        FileResource fr = new FileResource(coldestFileSoFar);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
        
            pl(record.get(UTC_DATE)+": "+record.get(TEMP_NAME));
        }
        
        return coldestFileSoFar.getName();
        
    }
    
    /**
     * Find CSVRecord of the file with the lowest humidity
     * @param   parser  CSVParser to be searched
     * @return  CSVRecord containing the lowest humidity
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
       
        for (CSVRecord record : parser) {
        
            // skip missing values ("N/A")
            if (record.get(HUM_NAME).equals("N/A")) continue; 
            
            if (lowestSoFar == null) lowestSoFar = record;
            else {
                
                double lowestHumiditySoFar = Double.parseDouble(lowestSoFar.get(HUM_NAME));
                double humidityCurrent = Double.parseDouble(record.get(HUM_NAME));
                if (lowestHumiditySoFar > humidityCurrent) lowestSoFar = record;
            
            }
            
        }
        return lowestSoFar;

    }
   
    /**
     * Find a lowest humidity in many files selected.
     * @return  CSVRecord found with the lowest humidity
     */
    public CSVRecord lowestHumidityInManyFiles() {
    
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        
        
        for (File file : dr.selectedFiles()) {
            FileResource fr = new FileResource(file);
            CSVParser parser = fr.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            
            if (lowest == null) lowest = current;
            else {
            
                double currentHum = Double.parseDouble(current.get(HUM_NAME));
                double lowestHum = Double.parseDouble(lowest.get(HUM_NAME));
                
                if (currentHum < lowestHum) lowest = current;
                
            }
        }
        
        pl("Lowest Humidity was " + lowest.get(HUM_NAME) + " at " + lowest.get(UTC_DATE));
        
        return lowest;
    }
    /**
     * Find an average temperature of files selected, 
     * when humidity is greater or higher than given value
     * @param   parser  CSVParser to be used in search
     * @param   value   value of humidity to be checked
     * @return  average temperature for records matching the criteria. If no matching
     *          files then should return null and print message
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
    
        int count = 0;
        double tempSum = 0;
        
        for (CSVRecord record : parser) {
        
            int thisHum = Integer.parseInt(record.get(HUM_NAME));
            if (thisHum >= value) {
                count++;
                tempSum += Double.parseDouble(record.get(TEMP_NAME));
            }
        }
        
        if (count == 0)  {
            pl("No temperatures with that humidity");
            return - 9999.0d;
        }
        else { 
            double avgTemp = tempSum/count;
            pl("Average temperature when high Humidity is " + avgTemp);
            return avgTemp;
        }
 
    }
}
