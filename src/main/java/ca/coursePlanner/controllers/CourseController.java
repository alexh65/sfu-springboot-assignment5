package ca.coursePlanner.controllers;

import ca.coursePlanner.Observer.Observer;
import ca.coursePlanner.model.*;
import ca.coursePlanner.wrappers.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CourseController {
    private CSVParser csvParser = new CSVParser();
    private ArrayList<Department> departments = csvParser.getDepartments();
    private AtomicLong nextOfferingId = new AtomicLong();

    public void addDepartment(Department department) {
        departments.add(department);
    }

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
        if (course == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Offering> offerings = course.getOfferings();
        for(Offering o : offerings){
            result.add(ApiCourseOfferingWrapper.getCourseOfferingWrapper(o));
        }
        Collections.sort(result);
        return result;
    }

    @GetMapping("/api/departments/{deptId}/courses/{courseId}/offerings/{offeringId}")
    public ArrayList<ApiOfferingSectionWrapper> getSections(@PathVariable("deptId") long deptId,
                                                            @PathVariable("courseId") long courseId,
                                                            @PathVariable("offeringId") long offeringId){
        ArrayList<ApiOfferingSectionWrapper> result = new ArrayList<>();
        Course course = departments.get((int) findIndexOfDepartment(deptId)).getCourseById(courseId);
        if (course == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Offering> offerings = course.getOfferings();
        for(Offering o : offerings){
            if (o.getCourseOfferingId() == offeringId) {
                ArrayList<Section> sections = o.getSections();
                for(Section s: sections){
                    result.add(ApiOfferingSectionWrapper.getOfferingSectionWrapper(s));
                }
            }
        }
        //if none of the offers has the offering id
        if (result.size() == 0){
            throw new IllegalStateException();
        }
        return result;
    }

    public Department findDepartment(long id){
        for (Department department : departments) {
            if (department.getId() == id) {
                return department;
            }
        }
        throw new NullPointerException();
    }

    public long findIndexOfDepartment(long id){
        for (int i = 0; i < departments.size(); i++) {
            if(departments.get(i).getId() == id){
                return i;
            }
        }
        throw new NullPointerException();
    }

    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSection(@RequestBody ApiOfferingDataWrapper wrapper) {
        String[] instructors = wrapper.instructor.split(",");
        Semester semester = new Semester(wrapper.semester);
        Offering offering = new Offering(nextOfferingId.incrementAndGet(), semester, wrapper.location,
                wrapper.enrollmentCap, wrapper.component, wrapper.enrollmentTotal, instructors, wrapper.instructor);

        csvParser.addToObjects(offering, wrapper.subjectName, wrapper.catalogNumber);

        String event = getDate() + " Added section " + wrapper.component + " with enrollment (" +
                wrapper.enrollmentTotal + " / " + wrapper.enrollmentCap + " to offering " + semester.getTerm() +
                semester.getYear();

        WatcherController watcherController = new WatcherController();
        watcherController.notifyObservers(event);
    }


    //Date Tutorial: https://beginnersbook.com/2013/05/current-date-time-in-java/
    private String getDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        return time.replaceAll("\\.", "");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The ID of the department does not exist")
    @ExceptionHandler(NullPointerException.class)
    public void departmentIdNotFoundExceptionHandler(){}

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The ID of the course does not exist")
    @ExceptionHandler(IllegalArgumentException.class)
    public void courseIdNotFoundExceptionHandler(){}

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The ID of the offering does not exist")
    @ExceptionHandler(IllegalStateException.class)
    public void offeringIdNotFoundExceptionHandler(){}
}