package ca.coursePlanner.wrappers;
import java.util.List;

public class ApiWatcherWrapper {
    public long id;
    public ApiDepartmentWrapper department;
    public ApiCourseWrapper course;
    public List<String> events;

    public static ApiWatcherWrapper makeWatcherWrapper (long watcherId, ApiDepartmentWrapper department,
                                                        ApiCourseWrapper course, List<String> events){
        ApiWatcherWrapper result = new ApiWatcherWrapper();
        result.id = watcherId;
        result.department = department;
        result.course = course;
        result.events = events;

        return result;
    }

}
