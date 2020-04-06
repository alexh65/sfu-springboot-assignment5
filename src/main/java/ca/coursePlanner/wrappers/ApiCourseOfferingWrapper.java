package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.Offering;

public class ApiCourseOfferingWrapper {
    public long courseOfferingId;
    public String location;
    public String instructors;
    public String term;
    public long semesterCode;
    public int year;

    public static ApiCourseOfferingWrapper getCourseOfferingWrapper(Offering o){
        ApiCourseOfferingWrapper result = new ApiCourseOfferingWrapper();
        result.courseOfferingId = o.getCourseOfferingId();
        result.location = o.getLocation();
        result.instructors = o.getInstructorInString();
        result.term = o.getSemester().getTerm();
        result.semesterCode = o.getSemester().getSemesterCode();
        result.year = o.getSemester().getYear();
        return result;
    }
}
