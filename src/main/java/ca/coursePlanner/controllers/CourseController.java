package ca.coursePlanner.controllers;

import ca.coursePlanner.model.CSVParser;
import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Department;
import ca.coursePlanner.model.Offering;
import ca.coursePlanner.wrappers.ApiCourseOfferingWrapper;
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
        ArrayList<Course> courses = departments.get((int) findIndexOfDepartment(id)).getCourses();
        for (Course c : courses) {
            result.add(ApiCourseWrapper.makeNewWrapper(c.getId(), c.getCatalogNumber()));
        }
        return result;
    }

    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings")
    public ArrayList<ApiCourseOfferingWrapper> getOfferings(@PathVariable("deptId") long deptId,
                                                            @PathVariable("courseId") long courseId){
        ArrayList<ApiCourseOfferingWrapper> result = new ArrayList<>();
        Course course = departments.get((int) findIndexOfDepartment(deptId)).getCourseById(courseId);
        ArrayList<Offering> offerings = course.getOfferings();
        for(Offering o : offerings){
            result.add(ApiCourseOfferingWrapper.getCourseOfferingWrapper(o));
        }
        return result;
    }

    private long findIndexOfDepartment(long id){
        for (int i = 0; i < departments.size(); i++) {
            if(departments.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
}
