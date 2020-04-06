package ca.coursePlanner.model;

public class Offering {
    private long courseOfferingId;
    private Semester semester;
    private String location;
    private int enrollmentCap;
    private String component;
    private int enrollmentTotal;
    private String[] instructor;
    private String instructorString;

    public Offering(long courseOfferingId, Semester semester, String location, int enrollmentCap, String component,
                    int enrollmentTotal, String[] instructor, String instructorString) {
        this.courseOfferingId = courseOfferingId;
        this.semester = semester;
        this.location = location;
        this.enrollmentCap = enrollmentCap;
        this.component = component;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
        this.instructorString = instructorString;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getLocation() {
        return location;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public String getComponent() {
        return component;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public String getInstructorInString(){
        return instructorString;
    }

    public long getCourseOfferingId() {
        return courseOfferingId;
    }
}
