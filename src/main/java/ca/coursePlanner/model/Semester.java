package ca.coursePlanner.model;

/**
 * Class stores information on a single semester.
 */
public class Semester {
    private int year;
    private int semester;

    public Semester(String semester) {
        this.year = Integer.parseInt(semester.substring(0, 3));
        this.semester = Integer.parseInt(semester.substring(3));
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return "" + year + semester;
    }
}
