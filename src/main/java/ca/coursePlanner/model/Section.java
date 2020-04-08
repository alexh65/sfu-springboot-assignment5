package ca.coursePlanner.model;

public class Section {
    private String type;
    private int enrollmentCap;
    private int enrollmentTotal;

    public Section(String type, int enrollmentCap, int enrollmentTotal) {
        this.type = type;
        this.enrollmentCap = enrollmentCap;
        this.enrollmentTotal = enrollmentTotal;
    }

    public String getType() {
        return type;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }
}
