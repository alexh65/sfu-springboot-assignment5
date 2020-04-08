package ca.coursePlanner.model;

import java.util.ArrayList;

public class Offering {
    private long courseOfferingId;
    private Semester semester;
    private String location;
    private String[] instructor;
    private String instructorString;
    private ArrayList<Section> sections;

    public Offering(long courseOfferingId, Semester semester, String location, String[] instructor, String instructorString) {
        this.courseOfferingId = courseOfferingId;
        this.semester = semester;
        this.location = location;
        this.instructor = instructor;
        this.instructorString = instructorString;
        this.sections = new ArrayList<>();
    }

    public Semester getSemester() {
        return semester;
    }

    public String getLocation() {
        return location;
    }

    public String getInstructorInString(){
        return instructorString;
    }
    public void addSection(String type, int enrollmentCap, int enrollmentTotal){
        sections.add(new Section(type, enrollmentCap, enrollmentTotal));
    }
    public ArrayList<Section> getSections(){
        return sections;
    }
    public long getCourseOfferingId() {
        return courseOfferingId;
    }
}
