package ca.coursePlanner.controllers;

import ca.coursePlanner.model.CSVParser;
import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Department;
import ca.coursePlanner.wrappers.ApiCourseWrapper;
import ca.coursePlanner.wrappers.ApiDepartmentWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CourseController {
    CSVParser csvParser = new CSVParser();
    ArrayList<Department> departments = csvParser.getDepartments();

    @GetMapping("/api/departments")
    public ArrayList<ApiDepartmentWrapper> getDepartments(){
        ArrayList<ApiDepartmentWrapper> result = new ArrayList<>();
        for(Department d: departments){
            result.add(ApiDepartmentWrapper.makeNewWrapper(d.getId(), d.getName()));
        }
        return result;
    }

    @GetMapping("/api/departments/{id}/courses")
    public ArrayList<ApiCourseWrapper> getCourses(@PathVariable("id") long id){
        ArrayList<ApiCourseWrapper> result = new ArrayList<>();
        for(Department d: departments){
            if(d.getId() == id){
                ArrayList<Course> courses = d.getCourses();
                for(Course c: courses){
                    result.add(ApiCourseWrapper.makeNewWrapper(c.getId(), c.getCatalogNumber()));
                }
                break;
            }
        }
        return result;
    }
}
