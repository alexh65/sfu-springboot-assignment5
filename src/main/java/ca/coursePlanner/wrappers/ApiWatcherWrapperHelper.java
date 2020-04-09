package ca.coursePlanner.wrappers;

public class ApiWatcherWrapperHelper {
    public long deptId;
    public long courseId;

    public static ApiWatcherWrapperHelper makeNewWrapper (long deptId, long courseId){
        ApiWatcherWrapperHelper result = new ApiWatcherWrapperHelper();
        result.deptId = deptId;
        result.courseId = courseId;
        return result;
    }
}
