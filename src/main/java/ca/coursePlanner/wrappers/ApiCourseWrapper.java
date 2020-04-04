package ca.coursePlanner.wrappers;

public class ApiCourseWrapper {
    public long courseId;
    public String catalogNumber;

    public static ApiCourseWrapper makeNewWrapper (long courseId, String catalogNumber){
        ApiCourseWrapper result = new ApiCourseWrapper();
        result.courseId = courseId;
        result.catalogNumber = catalogNumber;
        return result;
    }
}
