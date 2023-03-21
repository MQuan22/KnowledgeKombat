package com.example.knowledgekombat.service;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.Question;
import com.example.knowledgekombat.model.User_Course;
import com.example.knowledgekombat.payload.AnswerRequest;
import com.example.knowledgekombat.payload.CoursePayload;
import com.example.knowledgekombat.payload.CourseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    public Course createCourse(CoursePayload coursePayload);
    public List<Answer> getAnswersByQuestionId(Long Id);
    public List<Question> getQuestionsByCourseId(Long Id);
    public Course editCourse(CoursePayload coursePayload, Long courseId);
    public List<CourseResponse> getAllCourses();
    public CourseResponse getCourseById(Long Id);
    public CourseResponse deleteCourseById(Long id);
}
