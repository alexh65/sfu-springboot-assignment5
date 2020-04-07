package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.CourseData;
import ca.coursePlanner.model.Offering;
import ca.coursePlanner.model.Semester;

public class ApiOfferingDataWrapper {
    public Semester semester;
    public String subjectName;
    public String catalogNumber;
    public String location;
    public int enrollmentCap;
    public int enrollmentTotal;
    public String component;
    public String instructor;

    public ApiOfferingDataWrapper getDataOfferingWrapper(CourseData courseData){
        ApiOfferingDataWrapper result = new ApiOfferingDataWrapper();
        result.semester = courseData.getSemester();
        result.subjectName = courseData.getSubject();
        result.catalogNumber = courseData.getCatalogNumber();
        result.location = courseData.getLocation();
        result.enrollmentCap = courseData.getEnrollmentCapacity();
        result.enrollmentTotal = courseData.getEnrollmentTotal();
        result.component = courseData.getComponentCode();
        result.instructor = courseData.stringInstructors();

        return result;
    }
}
