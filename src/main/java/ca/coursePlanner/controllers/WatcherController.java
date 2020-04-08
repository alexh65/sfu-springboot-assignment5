package ca.coursePlanner.controllers;

import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Department;
import ca.coursePlanner.wrappers.ApiCourseWrapper;
import ca.coursePlanner.wrappers.ApiDepartmentWrapper;
import ca.coursePlanner.wrappers.ApiWatcherWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WatcherController {
    ArrayList<ApiWatcherWrapper> watchers = new ArrayList<>();
    AtomicLong watcherId = new AtomicLong();
    CourseController courseController = new CourseController();
    ArrayList<Department> departments = courseController.getDepartmentList();

    @GetMapping("/api/watchers")
    public ArrayList<ApiWatcherWrapper> listWatchers(){
        return watchers;
    }

    //Date Tutorial: https://beginnersbook.com/2013/05/current-date-time-in-java/
    private String getDate() {
        SimpleDateFormat formatter= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);
        return time.replaceAll("\\.", "");
    }

    @PostMapping("/api/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatcher(@RequestBody int deptId, @RequestBody int courseId){
        Department department = departments.get(deptId);
        Course course = department.getCourses().get(courseId);
        List<String> events = new ArrayList<>();
        events.add(getDate());

        ApiWatcherWrapper wrapper = ApiWatcherWrapper.makeWatcherWrapper(watcherId.getAndIncrement(),
                ApiDepartmentWrapper.makeNewWrapper(department.getId(), department.getName()),
                ApiCourseWrapper.makeNewWrapper(course.getId(), course.getCatalogNumber()), events);
        watchers.add(wrapper);
        System.out.println("At end, list is:");
        System.out.println(watchers);
    }

    @GetMapping("/api/watchers/{watcherId}")
    public ApiWatcherWrapper getOneWatcher(@PathVariable("watcherId") long watcherId) {
        for (ApiWatcherWrapper watcher : watchers) {
            if (watcher.id == watcherId) {
                return watcher;
            }
        }
        throw new IllegalStateException();
    }

    @DeleteMapping("/api/watchers/{watcherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWatcher(@PathVariable("watcherId") long watcherId) {
        for (int i = 0; i < watchers.size(); i++) {
            ApiWatcherWrapper watcher = watchers.get(i);
            if (watcher.id == watcherId) {
                watchers.remove(i);
            }
        }
        throw new IllegalStateException();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The ID of the watcher does not exist")
    @ExceptionHandler(IllegalStateException.class)
    public void offeringIdNotFoundExceptionHandler(){}
}
