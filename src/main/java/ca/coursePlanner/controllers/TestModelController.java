package ca.coursePlanner.controllers;

import ca.coursePlanner.model.CSVParser;
import ca.coursePlanner.model.CourseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Hashtable;

@RestController
public class TestModelController {
    Hashtable<String, ArrayList<CourseData>> coursesAndNumber = new CSVParser().getCoursesAndNumber();

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