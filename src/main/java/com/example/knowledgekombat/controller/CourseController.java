package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.payload.CoursePayload;
import com.example.knowledgekombat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> addCourse(@RequestBody CoursePayload coursePayload){
        Course course = courseService.createCourse(coursePayload);
        return ResponseEntity.ok(course);
    }
}
