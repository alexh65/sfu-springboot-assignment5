package ca.coursePlanner.model;

/**
 * Class stores information on a single semester.
 */
public class Semester {
    private long semesterCode;
    private int year;
    private String term;

    public Semester(String semester) {
        this.semesterCode = Long.parseLong(semester);
        this.year = convertToYear(semester.substring(0, 3));
        switch(Integer.parseInt(semester.substring(3))){
            case 1: term = "Spring"; break;
            case 4: term = "Summer"; break;
            case 7: term = "Fall"; break;
        }
    }

    private int convertToYear(String code) {
        return 1900 + 100 * (Integer.parseInt(""+ code.charAt(0))) + 10 * (Integer.parseInt(""+ code.charAt(1)))
                + (Integer.parseInt(""+ code.charAt(2)));
    }

    public int getYear() {
        return year;
    }

    public long getSemesterCode() {
        return semesterCode;
    }

    public String getTerm(){
        return term;
    }

    @Override
    public String toString() {
        return "" + semesterCode;
    }
}