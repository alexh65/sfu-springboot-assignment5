package ca.coursePlanner.controllers;

import ca.coursePlanner.Observer.Observer;
import ca.coursePlanner.model.Course;
import ca.coursePlanner.model.Department;
import ca.coursePlanner.wrappers.ApiCourseWrapper;
import ca.coursePlanner.wrappers.ApiDepartmentWrapper;
import ca.coursePlanner.wrappers.ApiWatcherWrapper;
import ca.coursePlanner.wrappers.ApiWatcherWrapperHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WatcherController {
    ArrayList<ApiWatcherWrapper> watchers = new ArrayList<>();
    ArrayList<Observer> observers = new ArrayList<>();
    AtomicLong watcherId = new AtomicLong();
    CourseController courseController = new CourseController();

    @GetMapping("/api/watchers")
    public ArrayList<ApiWatcherWrapper> listWatchers(){
        return watchers;
    }

    @PostMapping("/api/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWatcher(@RequestBody ApiWatcherWrapperHelper wrapperId){
        Department department = courseController.findDepartment(wrapperId.deptId);
        Course course = department.getCourseById(wrapperId.courseId);

        ApiWatcherWrapper wrapper = ApiWatcherWrapper.makeWatcherWrapper(watcherId.getAndIncrement(),
                ApiDepartmentWrapper.makeNewWrapper(department.getId(), department.getName()),
                ApiCourseWrapper.makeNewWrapper(course.getId(), course.getCatalogNumber()));
        watchers.add(wrapper);
        course.addObserver(event -> {
            wrapper.addEvents(event);
        });
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

    public void notifyObservers(String event) {
        for (Observer observer : observers) {
            observer.stateChanged(event);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The ID of the watcher does not exist")
    @ExceptionHandler(IllegalStateException.class)
    public void offeringIdNotFoundExceptionHandler(){}
}