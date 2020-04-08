package ca.coursePlanner.wrappers;

import ca.coursePlanner.model.Offering;
import org.jetbrains.annotations.NotNull;

public class ApiCourseOfferingWrapper implements Comparable{
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

    @Override
    public int compareTo(@NotNull Object o) {
        ApiCourseOfferingWrapper wrapper = (ApiCourseOfferingWrapper) o;
        return Long.compare(this.semesterCode, wrapper.semesterCode);
    }
}
