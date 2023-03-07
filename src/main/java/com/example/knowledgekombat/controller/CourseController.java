package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.Question;
import com.example.knowledgekombat.payload.CoursePayload;
import com.example.knowledgekombat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getAnswer/{id}")
    public ResponseEntity<?> getAnswersByQuestionId(@PathVariable(name = "id")Long questionId){
        List<Answer> answerList = courseService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answerList);
    }
    @GetMapping("/getQuestion/{id}")
    public ResponseEntity<?> getQuestionsByCourseId(@PathVariable(name = "id")Long courseId){
        List<Question> questionList = courseService.getQuestionsByCourseId(courseId);
        return ResponseEntity.ok(questionList);
    }
}
