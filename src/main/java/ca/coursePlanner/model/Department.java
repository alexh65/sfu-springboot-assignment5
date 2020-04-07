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

    public Course getCourseByCatalogNumber(String catalogNumber) {
        for (Course course : courses) {
            if (course.getCatalogNumber().equals(catalogNumber)) {
                return course;
            }
        }
        return null;
    }

    public Course getCourseById(long id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        courses.add(course);
    }

    public boolean hasCourse(String course){
        for(Course c : courses){
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