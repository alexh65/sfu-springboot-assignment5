package ca.coursePlanner;

import ca.coursePlanner.model.CourseData;
import ca.coursePlanner.model.CSVParser;

import java.util.ArrayList;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {
        CSVParser CSVParser = new CSVParser();
        Hashtable<String, ArrayList<CourseData>> courseHashTable = CSVParser.getCourses();

        if (courseHashTable == null) {
            return;
        } else {
//
//            for (String key : courseHashTable.keySet()) {
//                System.out.println(key + " = " + courseHashTable.get(key));
//                System.out.println();
//            }

            printCourses(courseHashTable);
        }
    }

    private static void printCourses(Hashtable<String, ArrayList<CourseData>> courseHashTable) {
        for (String key : courseHashTable.keySet()) {
            System.out.println(key);
            printList(courseHashTable.get(key));
        }
    }

    private static void printList(ArrayList<CourseData> currentList) {
        final String TAB = "    ";
        for (CourseData course : currentList) {
            System.out.println(TAB + course.toStringFirstLine());
            System.out.println(TAB + TAB + course.toStringSecondLine());
        }
    }
}
