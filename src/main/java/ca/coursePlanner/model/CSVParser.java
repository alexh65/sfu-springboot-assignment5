package ca.coursePlanner.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class parses the csv ArrayList into a hashtable, where the key is the catalog number and the element is an
 * an ArrayList of CourseData corresponding to the catalog number.
 */
public class CSVParser {
    private Hashtable<String, ArrayList<CourseData>> courses = new Hashtable<>();
    private Hashtable<String, ArrayList<CourseData>> coursesAndNumber = new Hashtable<>();
    private ArrayList<Department> departments = new ArrayList<>();

    AtomicInteger departmentId = new AtomicInteger(1);
    AtomicInteger courseId = new AtomicInteger(1);
    AtomicInteger offeringId = new AtomicInteger(1);

    //Regex from: https://stackoverflow.com/questions/18893390/splitting-on-comma-outside-quotes
    private final String SPLIT_BY = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    private final String EXIT_CODE = "exit application";
    
    public CSVParser() {
        CSVReader reader = new CSVReader();
        ArrayList<String> csvList = reader.getCvsList();

        if (!csvList.get(0).equals(EXIT_CODE)) {
            parseCSVList(csvList);
            sortCourses();
            sortCourseInDepartments();
            fillCoursesAndNumber();
            Collections.sort(departments);
        } else {
            courses = null;
            departments = null;
        }
    }

    private void sortCourseInDepartments() {
        for(Department d: departments){
            d.sortCourses();
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
        for (String key : courses.keySet()) {
            ArrayList<CourseData> currentElement = courses.get(key);
            for (CourseData courseData : currentElement) {
                String currentKey = (key + " " + courseData.getCatalogNumber()).trim();
                if (coursesAndNumber.containsKey(currentKey)) {
                    coursesAndNumber.get(currentKey).add(courseData);
                } else {
                    ArrayList<CourseData> courseList = new ArrayList<>();
                    courseList.add(courseData);
                    coursesAndNumber.put(currentKey, courseList);
                }
            }
        }
    }

    private void parseCSVList(ArrayList<String> csvList) {
        for (String line : csvList) {
            String[] splitLine = line.split(SPLIT_BY);
            formatObjects(splitLine);
        }
    }

    private void formatObjects(String[] splitLine) {
        Semester semester = new Semester(splitLine[0]);
        String subject = splitLine[1].trim();
        String catalogNumber = splitLine[2].trim();
        String location = splitLine[3].trim();
        int enrollmentCap = Integer.parseInt(splitLine[4]);
        int enrollmentTotal = Integer.parseInt(splitLine[5]);
        String[] instructors = separateInstructors(splitLine[6].trim());
        String componentCode = splitLine[7].trim();

        addToObjects(semester, location, enrollmentCap,
                componentCode, enrollmentTotal, instructors, splitLine[6].trim(), subject, catalogNumber);

        //For dump-model
        CourseData courseData = new CourseData(semester, subject, catalogNumber, location, enrollmentCap,
                enrollmentTotal, instructors, componentCode);

        if (courses.containsKey(subject)) {
            courses.get(subject).add(courseData);
        } else {
            ArrayList<CourseData> courseList = new ArrayList<>();
            courseList.add(courseData);
            courses.put(subject, courseList);
        }

    }

    public void addToObjects(Semester semester, String location, int enrollmentCap,
                             String componentCode, int enrollmentTotal, String[] instructors, String instructorString,
                             String subject, String catalogNumber) {
        Department department = null;
        if (!hasSubject(subject)){
            department = new Department(departmentId.getAndIncrement(), subject);
            departments.add(department);
        } else {
            long index = findIndex(subject);
            if(index == -1){
                System.exit(-1);
            } else {
                department = departments.get((int)index);
            }
        }

        Course course = null;
        if (!department.hasCourse(catalogNumber)) {
            course = new Course(courseId.getAndIncrement(), catalogNumber);
            department.addCourse(course);
        } else {
            course = department.getCourseByCatalogNumber(catalogNumber);
        }

        Offering offering = course.getOffering(semester, instructorString);
        if(offering == null) {
            offering = new Offering(offeringId.getAndIncrement(), semester, location, instructors, instructorString);
            course.addOffering(offering);
        }
        offering.addSection(componentCode, enrollmentCap, enrollmentTotal);
    }

    private boolean hasSubject(String subject) {
        for(Department d: departments){
            String existed = d.getName();
            if (existed.equals(subject)){
                return true;
            }
        }
        return false;
    }

    public long findIndex(String subject) {
        for(int i = 0; i < departments.size(); i++){
            if(departments.get(i).getName().equals(subject)){
                return i;
            }
        }
        return -1;
    }

    public String[] separateInstructors(String instructors) {
        if(instructors.equals("<null>")){
            return new String[]{""};
        }
        return instructors.split(",");
    }


    public Hashtable<String, ArrayList<CourseData>> getCoursesAndNumber() {
        return coursesAndNumber;
    }
    public ArrayList<Department> getDepartments() {
        return departments;
    }

}