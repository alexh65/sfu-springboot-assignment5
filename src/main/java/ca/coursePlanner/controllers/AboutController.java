package ca.coursePlanner.controllers;

import ca.coursePlanner.model.CSVParser;
import ca.coursePlanner.model.CourseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

@RestController
public class AboutController {
    Hashtable<String, ArrayList<CourseData>> coursesAndNumber = new CSVParser().getCoursesAndNumber();

    @GetMapping("/api/about")
    public Map<String, String> about(){
        Hashtable<String, String> result = new Hashtable<>();
        result.put("appName", "Awesome Course Planner");
        result.put("authorName", "Riya Dhariwal and Alex Hoang");
        return result;
    }

    @GetMapping("/api/dump-model")
    public void modelDump(){
        final String TAB = "    ";
        if (coursesAndNumber == null) {
            return;
        } else {
            for (String key: coursesAndNumber.keySet()) {
                System.out.println(key);
                for (CourseData course: coursesAndNumber.get(key)){
                    System.out.println(TAB + course.toStringFirstLine());
                    System.out.println(TAB + TAB + course.toStringSecondLine());
                }
            }
        }
    }
}