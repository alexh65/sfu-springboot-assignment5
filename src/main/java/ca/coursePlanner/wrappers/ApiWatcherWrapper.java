package ca.coursePlanner.wrappers;
import ca.coursePlanner.Observer.Observer;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class ApiWatcherWrapper {
    public long id;
    public ApiDepartmentWrapper department;
    public ApiCourseWrapper course;
    public List<String> events;

    public static ApiWatcherWrapper makeWatcherWrapper (long watcherId, ApiDepartmentWrapper department,
                                                        ApiCourseWrapper course){
        ApiWatcherWrapper result = new ApiWatcherWrapper();
        result.id = watcherId;
        result.department = department;
        result.course = course;
        result.events = new ArrayList<>();

        return result;
    }

    public void addEvents(String event) {
        events.add(event);
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
