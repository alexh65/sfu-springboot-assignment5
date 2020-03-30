package ca.coursePlanner;

import ca.coursePlanner.model.CSVReader;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        CSVReader cvs = new CSVReader();
        ArrayList<String> test = cvs.getCvsList();

        for (String s : test) {
            System.out.println(s);
        }
    }
}
