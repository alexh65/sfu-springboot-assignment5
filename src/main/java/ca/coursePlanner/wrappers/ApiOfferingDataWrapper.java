package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.CourseData;

public class ApiOfferingDataWrapper {
    public String semester;
    public String subjectName;
    public String catalogNumber;
    public String location;
    public int enrollmentCap;
    public String component;
    public int enrollmentTotal;
    public String instructor;

    public ApiOfferingDataWrapper getDataOfferingWrapper(CourseData courseData){
        ApiOfferingDataWrapper result = new ApiOfferingDataWrapper();
        result.semester = courseData.getSemester().getTerm();
        result.subjectName = courseData.getSubject();
        result.catalogNumber = courseData.getCatalogNumber();
        result.location = courseData.getLocation();
        result.enrollmentCap = courseData.getEnrollmentCapacity();
        result.component = courseData.getComponentCode();
        result.enrollmentTotal = courseData.getEnrollmentTotal();
        result.instructor = courseData.stringInstructors();

        return result;
    }
}
