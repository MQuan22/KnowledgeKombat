package com.example.knowledgekombat.service;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.Question;
import com.example.knowledgekombat.payload.CoursePayload;

import java.util.List;

public interface CourseService {
    public Course createCourse(CoursePayload coursePayload);
    public List<Answer> getAnswersByQuestionId(Long Id);
    public List<Question> getQuestionsByCourseId(Long Id);
}
