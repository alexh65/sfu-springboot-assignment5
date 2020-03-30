package ca.coursePlanner.model;

import java.util.ArrayList;

/**
 * Class stores information on a single course.
 */
public class CourseData {
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private String location;
    private int enrollmentCapacity;
    private int enrollmentTotal;
    private ArrayList<String> instructors;
    private String componentCode;

    public CourseData(Semester semester, String subject, String catalogNumber, String location, int enrollmentCapacity,
                      int enrollmentTotal, ArrayList<String> instructors, String componentCode) {
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCapacity = enrollmentCapacity;
        this.enrollmentTotal = enrollmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getSubject() {
        return subject;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }
}
