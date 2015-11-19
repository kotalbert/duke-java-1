
/**
 * Class for reading CSV Exports data.
 * Colums:
 * Country,Exports,Value (dollars)
 * @author Pawel Daniluk
 * @version 2015-10-25
 */

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVExportsData {
    
    private <T> void p(T t){System.out.print(t);}
    private <T> void pl(T t){System.out.println(t);}
    
    /**
     * Print country info or "Not found".
     * @param   parser  CSVParser
     * @param   country name of country to be searched
     */
    public void countryInfo(CSVParser parser, String country){
    
        boolean found = false;
        
        for  (CSVRecord record : parser) {
        
            String cntry = record.get("Country");
            if (cntry.toUpperCase().equals(country.toUpperCase())) {
                p(cntry+": ");
                p(record.get("Exports")+": ");
                pl(record.get("Value (dollars)"));
                found = true;
                break;
            }
        }
    
        if (!found) pl(country+" not found.");
    }
    
    /**
     * Print list of countries exporting two given export items.
     * @param   parser  CSVParser
     * @param   exportItem1 first export item to be found
     * @param   exportItem2 second export item to be found
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        boolean found = false;
        pl("Exporters of "+exportItem1+" and "+exportItem2);
        
        for (CSVRecord record : parser) {
            
            String exports = record.get("Exports");
            if ((exports.contains(exportItem1)) && (exports.contains(exportItem2))) {
                found = true;
                pl(record.get("Country"));
            }
           
        }
        
        if (!found) pl("Not found");
    }
    
    /**
     * Print a number of countries exporting given export item.
     * @param   parser  CSVParser
     * @param   exportItem  name of export item to be found
     */
    public void numberOfExporters(CSVParser parser, String exportItem) {
        int exportersCount = 0;
        p("Exporters of "+exportItem+": ");
        for (CSVRecord record : parser) {
        
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) exportersCount++;
        }
        pl(exportersCount);
    }
    
    /**
     * Print a countries whose Value string is longer that amount parameter.
     * @param   parser  CSVParser
     * @param   amount  string representing dollar value, with 
     *          comas separating thousands, ie.: "$400,000,000"
     */
    public void bigExporters(CSVParser parser, String amount) {
    
        boolean found = false;
        int len = amount.length();
        for (CSVRecord record : parser) {
            String recAmount = record.get("Value (dollars)");
            int recLen = recAmount.length();
        
            if (recLen > len) {
                
                found = true;
                String cntry = record.get("Country");
                pl(cntry+" "+recAmount);
            }
            
            if (!found) pl("Not found.");
            
        }
    
    }
    
}
