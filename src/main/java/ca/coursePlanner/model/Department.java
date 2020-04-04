package ca.coursePlanner.model;

import java.util.ArrayList;
import java.util.Collections;

public class Department implements Comparable<Department>{
    private long id;
    private String name;
    private ArrayList<Course> courses;

    public Department(long id, String name){
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse(String catalogNumber) {
        return courses.get(findIndex(catalogNumber));
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    private int findIndex(String catalogNumber) {
        for(int i = 0; i < courses.size(); i++){
            if(courses.get(i).getCatalogNumber().equals(catalogNumber)){
                return i;
            }
        }
        return -1;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean hasCourse(String course){
        for(Course c: courses){
            String existed = c.getCatalogNumber();
            if (existed.equals(course)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id: " + id + ", name: " + name + "}";
    }

    @Override
    public int compareTo(Department department) {
        return name.compareTo(department.name);
    }

    public void sortCourses() {
        Collections.sort(courses);
    }
}
