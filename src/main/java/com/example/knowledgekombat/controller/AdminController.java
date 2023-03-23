package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.service.CourseService;
import com.example.knowledgekombat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CourseService courseService;
    private final UserService userService;

    @Autowired
    public AdminController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public int getTotalUsers(){
        int userAmount = userService.getUserCount();
        return userAmount;
    }

    @GetMapping("/courses")
    public int getTotalCourses(){
        int courseAmount = courseService.getAllCourses().size();
        return courseAmount;
    }

}
