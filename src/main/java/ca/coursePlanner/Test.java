package ca.coursePlanner;

import ca.coursePlanner.model.CSVReader;
import ca.coursePlanner.model.CourseData;
import ca.coursePlanner.model.CourseDataManager;

import java.util.ArrayList;
import java.util.Hashtable;

public class Test {

    public static void main(String[] args) {
        CSVReader cvs = new CSVReader();
        ArrayList<String> csvTest = cvs.getCvsList();

//        for (String s : csvTest) {
//            System.out.println(s);
//        }

        CourseDataManager courseDataManager = new CourseDataManager();
        Hashtable<String, ArrayList<CourseData>> hashTest = courseDataManager.getCourses();
        for (String key : hashTest.keySet()) {
            System.out.println(key + " = " + hashTest.get(key));
            System.out.println();
        }
    }
}
