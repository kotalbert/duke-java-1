
/**
 * Reading CSV data on names popularity by year in the US.
 * 
 * @author Pawel Daniluk
 * @version 2015-10-29
 */

import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class BabyNames {
    
    private <T> void pl(T t) {System.out.println(t);}
    
    /**
     * print the number of unique girls names , 
     * the number of unique boys names and the total names in the file.
     * @param   fr    FileResource pointing to the file to be read
     */
    public void totalBirths(FileResource fr) {
    
        CSVParser parser = fr.getCSVParser(false); 
        
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        for (CSVRecord record : parser) {
            
            int births =  Integer.parseInt(record.get(2));
            totalBirths += births;
            
            if (record.get(1).equals("M")) totalBoys += births;
            else totalGirls += births;
        
        }
        
        pl("Total births: " + totalBirths);
        pl("Girls births: " + totalGirls);
        pl("Boys births: " + totalBoys);
    }
    
    /**
     * print the number of unique girls names , 
     * the number of unique boys names and the total names in the selected file.
     */
    public void totalBirths() {
    
        FileResource fr = new FileResource();
        totalBirths(fr);
        
    }
    
    /**
     * Helper factory for crating CSVParser objects
     * @param   year    year identitying the file
     * @return  CSVParser from file
     */
    private CSVParser parserFactory(int year) {
        
        final String path =  "C:/Users/pd/Documents/Coursera/duke-java-1/BabyNames/data/us_babynames_by_year/";
        String fileName = path+"yob"+year+".csv";
        FileResource fr = new FileResource(fileName);
        return fr.getCSVParser(false);
        
    }
    
    /**
     * Helper factory for creating CSVParser from file
     * @param   file    file to be parsed
     * @retirm  CSVParser from file
     */
    private CSVParser parserFactory(File file) {
        
       FileResource fr = new FileResource(file);
       return fr.getCSVParser(false);
    
    }
    
    /**
     * This method returns the rank of the name in the file for the given gender, 
     * where rank 1 is the name with the largest number of births.
     * @param   year    year, identifying the file to be searched
     * @param   name    name to be ranked
     * @param   gender  gender to be ranked
     * @return  rank of the given name in a given year/gender
     *          -1 if not found
     */
    public int getRank(int year, String name, String gender) {
        
       CSVParser parser = parserFactory(year);
       return getRank(parser, name, gender);
 
    }
    
        /**
     * This method returns the rank of the name in the file for the given gender, 
     * where rank 1 is the name with the largest number of births.
     * @param   parser  CSVParser for file to be searched
     * @param   name    name to be ranked
     * @param   gender  gender to be ranked
     * @return  rank of the given name in a given year/gender
     *          -1 if not found
     */
    public int getRank(CSVParser parser, String name, String gender) {
    
    int rank = 0;
       
       for (CSVRecord record : parser) {
           
           if (record.get(1).equals(gender)) {
               
               rank++;
               // stop counting rank if name is found
               if (record.get(0).equals(name)) {
                   
                   return rank;
                }
            }
        }
    
        return -1;
    }
    
    /**
     * Returns a name with the given rank in file.
     * @param   year    year to be searched (identified file)  
     * @param   rank    rank to be found
     * @param   gender  gender to be rankded
     * @return  name with a given rank or "NO NAME" if no such rank in a file
     */
    public String getName(int year, int rank, String gender) {
        
        CSVParser parser = parserFactory(year);
        
        for (CSVRecord record : parser) {
        
            // skip if wrong gender
            if (!record.get(1).equals(gender)) continue;
            
            // return name if found matching rank
            String name = record.get(0);
            if (rank == getRank(year, name, gender)) return name;  
            
        }
        
        // return if rank not found
        return "NO NAME";
    }
    
    /**
     * This method determines what name would have been named if they were born in a different year, 
     * based on the same popularity.
     * @param   name    name to be searched
     * @param   year    year that name was born
     * @param   newYear year to be compared
     * @return  name of the same rank in newYear as name in year, "NO NAME" if not found
     */
    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
   
        int rank = getRank(year, name, gender);
        return getName(newYear, rank, gender);
        
    }
    
    /**
     * Helper function to extract year from filename
     */
    private int findYear(String fName) {
    
        // yob 2014 short.csv
        // 012 3456 789
        return Integer.parseInt(fName.substring(3, 7));
        
    }
    
    /**
     * This method selects a range of files to process and returns an integer, 
     * the year with the highest rank for the name and gender.
     * @param   name    name to be ranked over files
     * @param   gender  gender to be ranked
     * @return  highest rank for a given name over selected files
     */
    public int yearOfHighestRank(String name, String gender) {
    
        DirectoryResource dr = new DirectoryResource();
        int highestSoFar = -1;
        int yearOfRank = -1;
        String fileName;
        
        for (File file : dr.selectedFiles()) {
            
            String fName = file.getName();
            int year = findYear(fName);
            int rank = getRank(year, name, gender);
            
           
            // continue if rank not found in file
            if (rank == -1) continue;
            
            // for the first found rank
            if (highestSoFar == -1) {
            
                highestSoFar = rank;
                yearOfRank = year;
            }
            
            // for next ranks 
            // check if new rank is higher(lower is higher) than current highest
            if (highestSoFar > rank) {
                highestSoFar = rank;
                yearOfRank = year;
            }
            
            
        }
        
        return yearOfRank; 
    }
    
    /**
     * This method selects a range of files to process and returns a double representing 
     * the average rank of the name and gender over the selected files.
     * @param   name
     * @param   gender
     * @return  average rank for many files for a given name and given gender.
     *          - 1 if name not ranked in any of selected files.
     */
    public double getAverageRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int rankSum = 0;
        int rankCount = 0;
        
        for (File file : dr.selectedFiles()) {
        
            CSVParser parser = parserFactory(file);
            int rank = getRank(parser, name, gender);
            if (rank == - 1) continue;
            rankSum += rank;
            rankCount++;
            
        }
        
        if (rankCount == 0) return -1.0d;
        else return rankSum/(double)rankCount;
        
    }
    
    /**
     * This method returns an integer, the total number of births of those names with the same 
     * gender and same year who are ranked higher than name.
     * @param   year    year determining the file to be parsed
     * @param   name    name to be analized
     * @param   gender  geneder to be analized
     * @return  an integer, the total number of births of those names with the same gender and 
     *          same year who are ranked higher than name.
     */
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
    
        int nameRank = getRank(year, name, gender);
        CSVParser parser = parserFactory(year);
        int totalBirths = 0;
        for (CSVRecord record : parser) {
            // skip other gender
            if (!record.get(1).equals(gender)) continue;
            
            String recordName = record.get(0);
            int recordNameRank = getRank(year, recordName, gender);
            if (recordNameRank < nameRank) {
                totalBirths += Integer.parseInt(record.get(2));
            }
            else break;
        }
        
        
        return totalBirths;
    
    } 
    
}
