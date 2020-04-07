package ca.coursePlanner.controllers;

import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Department;
import ca.coursePlanner.model.Offering;
import ca.coursePlanner.model.Semester;
import ca.coursePlanner.wrappers.ApiOfferingDataWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.atomic.AtomicLong;

public class AddNewController {

    private AtomicLong nextCourseId = new AtomicLong();
    private AtomicLong nextDepartmentId = new AtomicLong();
    private AtomicLong nextOfferingId = new AtomicLong();

    @PostMapping("/api/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSection(@RequestBody ApiOfferingDataWrapper wrapper) {
        String[] instructors = wrapper.instructor.split(",");
        Offering offering = new Offering(nextOfferingId.incrementAndGet(), wrapper.semester, wrapper.location,
                wrapper.enrollmentCap, wrapper.component, wrapper.enrollmentTotal, instructors, wrapper.instructor);

        Course course = new Course(nextCourseId.incrementAndGet(), wrapper.catalogNumber);
        course.addOffering(offering);

        Department department = new Department(nextDepartmentId.incrementAndGet(), wrapper.subjectName);
        department.addCourse(course);

        CourseController courseController = new CourseController();
        courseController.addDepartment(department);
    }
}
