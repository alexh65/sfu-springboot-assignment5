package ca.coursePlanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Class parses the csv ArrayList into a hashtable, where the key is the catalog number and the element is an
 * an ArrayList of CourseData corresponding to the catalog number.
 */
public class CSVParser {
    private Hashtable<String, ArrayList<CourseData>> courses = new Hashtable<>();
    private Hashtable<String, ArrayList<CourseData>> coursesAndNumber = new Hashtable<>();
    //Regex from: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
    private final String SPLIT_BY = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    private final String EXIT_CODE = "exit application";

    public CSVParser() {
        CSVReader reader = new CSVReader();
        ArrayList<String> csvList = reader.getCvsList();

        if (!csvList.get(0).equals(EXIT_CODE)) {
            parseCSVList(csvList);
            sortCourses();
        } else {
            courses = null;
        }
    }

    private void sortCourses() {
        for (String key : courses.keySet()) {
            ArrayList<CourseData> newElement = courses.get(key);
            Collections.sort(newElement);
            courses.replace(key, courses.get(key), newElement);
        }
    }

    private void fillCoursesAndNumber() {

    }

    private void parseCSVList(ArrayList<String> csvList) {
        for (String line : csvList) {
            String[] splitLine = line.split(SPLIT_BY);
            addLineToHashTable(splitLine);
        }
    }

    private void addLineToHashTable(String[] splitLine) {
        Semester semester = new Semester(splitLine[0]);
        String subject = splitLine[1].trim();
        String catalogNumber = splitLine[2].trim();
        String location = splitLine[3].trim();
        int enrollmentCapacity = Integer.parseInt(splitLine[4]);
        int enrollmentTotal = Integer.parseInt(splitLine[5]);
        String[] instructors = separateInstructors(splitLine[6].trim());
        String componentCode = splitLine[7].trim();

        CourseData courseData = new CourseData(semester, subject, catalogNumber, location, enrollmentCapacity,
                enrollmentTotal, instructors, componentCode);

        if (courses.containsKey(subject)) {
            courses.get(subject).add(courseData);
        } else {
            ArrayList<CourseData> courseList = new ArrayList<>();
            courseList.add(courseData);
            courses.put(subject, courseList);
        }
    }

    private String[] separateInstructors(String instructors) {
        return instructors.split(",");
    }

    public Hashtable<String, ArrayList<CourseData>> getCourses() {
        return courses;
    }


}
