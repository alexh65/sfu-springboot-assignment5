package ca.coursePlanner.model;

public class Offering {
    private Semester semester;
    private String location;
    private int enrollmentCap;
    private String component;
    private int enrollmentTotal;
    private String[] instructor;

    public Offering(Semester semester, String location, int enrollmentCap, String component, int enrollmentTotal, String[] instructor) {
        this.semester = semester;
        this.location = location;
        this.enrollmentCap = enrollmentCap;
        this.component = component;
        this.enrollmentTotal = enrollmentTotal;
        this.instructor = instructor;
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

    public String[] getInstructor() {
        return instructor;
    }
}
