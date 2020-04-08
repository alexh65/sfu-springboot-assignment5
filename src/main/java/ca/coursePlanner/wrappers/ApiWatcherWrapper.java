package ca.coursePlanner.wrappers;
import ca.coursePlanner.Observer.Observer;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class ApiWatcherWrapper implements Observer {
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

    @Override
    public void stateChanged(ApiWatcherWrapper watcher) {
        this.id = watcher.id;
        this.department = watcher.department;
        this.course = watcher.course;
        this.events = watcher.events;
    }

    @Override
    public String toString() {
        return "ApiWatcherWrapper{" +
                "id=" + id +
                ", department=" + department +
                ", course=" + course +
                ", events=" + events +
                '}';
    }
}
