package ca.coursePlanner.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class reads in information from the csv file and puts it into an ArrayList.
 */
public class CSVReader {
    private ArrayList<String> csvList = new ArrayList<>();
    private final String FILE_NAME = "data/course_data_2018.csv";
    private final String EXIT_CODE = "exit application";

    //Tutorial: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
    public CSVReader() {
        Path csvFilePath = Paths.get(FILE_NAME);
        File csvFile = new File(String.valueOf(csvFilePath));

        if (csvFile.exists() && !csvFile.isDirectory()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(csvFile);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));

                String headerLine = reader.readLine();
                String currentLine = "";

                while ((currentLine = reader.readLine()) != null){
                    csvList.add(currentLine);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            csvList.add(EXIT_CODE);
        }
    }

    public ArrayList<String> getCvsList() {
        return csvList;
    }
}
