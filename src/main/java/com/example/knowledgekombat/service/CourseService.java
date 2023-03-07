package com.example.knowledgekombat.service;

import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.payload.CoursePayload;

public interface CourseService {
    public Course createCourse(CoursePayload coursePayload);
}
