package ca.coursePlanner.model;

import java.util.Arrays;

/**
 * Class stores information on a single course.
 */
public class CourseData implements Comparable{
    private Semester semester;
    private String subject;
    private String catalogNumber;
    private String location;
    private int enrollmentCapacity;
    private int enrollmentTotal;
    private String[] instructors;
    private String componentCode;

    public CourseData(Semester semester, String subject, String catalogNumber, String location, int enrollmentCapacity,
                      int enrollmentTotal, String[] instructors, String componentCode) {
        this.semester = semester;
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrollmentCapacity = enrollmentCapacity;
        this.enrollmentTotal = enrollmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;
    }

    public String stringInstructors() {
        String joinedList = instructors[0];
        for (int i = 1; i < instructors.length; i++) {
            joinedList += ", " + instructors[i];
        }
        return joinedList;
    }

    public String toStringFirstLine() {
        return "" + semester + " in " + location + " by " + stringInstructors();
    }

    public String toStringSecondLine() {
        return "Type=" + componentCode + ", Enrollment=" + enrollmentTotal + "/" + enrollmentCapacity;
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

    public String[] getInstructors() {
        return instructors;
    }

    public String getComponentCode() {
        return componentCode;
    }

    //Taken from: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    public boolean isNumeric(char str) {
        try {
            Integer.parseInt(String.valueOf(str));
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {

        StringBuilder currentBuilder = new StringBuilder();
//        System.out.println("this.catalogNumber = " + this.catalogNumber);
        for (int i = 0; i < this.catalogNumber.length(); i++) {
            if (isNumeric(this.catalogNumber.charAt(i))) {
                currentBuilder.append(this.catalogNumber.charAt(i));
            }
        }
        int currentCatalogNumber = Integer.parseInt(currentBuilder.toString());
//        System.out.println("int this = " + currentCatalogNumber);

        StringBuilder compareBuilder = new StringBuilder();
//        System.out.println("(CourseData)o).catalogNumber = " + ((CourseData)o).catalogNumber);
        for (int i = 0; i < ((CourseData)o).catalogNumber.length(); i++) {
            if (isNumeric(((CourseData)o).catalogNumber.charAt(i))) {
                compareBuilder.append(((CourseData)o).catalogNumber.charAt(i));
            }
        }

        int compareCatalogNumber = Integer.parseInt(compareBuilder.toString());
//        System.out.println("compare = " + compareCatalogNumber);

        return currentCatalogNumber - compareCatalogNumber;
    }
}
