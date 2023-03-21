package com.example.knowledgekombat.service.serviceImp;

import com.example.knowledgekombat.exception.CourseNotFoundException;
import com.example.knowledgekombat.model.*;
import com.example.knowledgekombat.payload.AnswerRequest;
import com.example.knowledgekombat.payload.CoursePayload;
import com.example.knowledgekombat.payload.CourseResponse;
import com.example.knowledgekombat.repository.*;
import com.example.knowledgekombat.security.UserPrincipal;
import com.example.knowledgekombat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UniRepository uniRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    @Autowired
    public CourseServiceImp(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository, UniRepository uniRepository, QuestionRepository questionRepository, AnswerRepository answerRepository){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.uniRepository = uniRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    @Transactional
    public Course createCourse(CoursePayload coursePayload) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User author = userRepository.findById(userPrincipal.getId()).orElseThrow(
                () -> new UsernameNotFoundException("Unauthorized"));
        Category category = categoryRepository.findByName(coursePayload.getCategory()).get();
        University university = uniRepository.findByName(coursePayload.getUniversity()).get();
        Course course = new Course();
        course.setName(coursePayload.getName());
        course.setCategory(category);
        course.setStatus(coursePayload.isStatus());
        course.setDescription(coursePayload.getDescription());
//        course.setImage(coursePayload.getImage());
        course.setUser(author);
        course.setUniversity(university);
        course.setQuestions(coursePayload.getQuestions());
        course.setTimeLimit(coursePayload.getTimeLimit());
        courseRepository.save(course);
//        questionRepository.saveAll(coursePayload.getQuestions());
        return course;
    }

    @Override
    @Transactional
    public List<Answer> getAnswersByQuestionId(Long Id) {
        List<Answer> answers = questionRepository.findAnswersByQuestionId(Id);
        return answers;
    }

    @Override
    @Transactional
    public List<Question> getQuestionsByCourseId(Long Id){
        return courseRepository.findQuestionByCourseId(Id);
    }

    @Override
    @Transactional
    public Course editCourse(CoursePayload coursePayload, Long courseId) {
        Category category = categoryRepository.findByName(coursePayload.getCategory()).get();
        University university = uniRepository.findByName(coursePayload.getUniversity()).get();
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new UsernameNotFoundException("Can't find Course"));
        course.setName(coursePayload.getName());
        course.setCategory(category);
        course.setStatus(coursePayload.isStatus());
        course.setDescription(coursePayload.getDescription());
//        course.setImage(coursePayload.getImage());
        course.setUniversity(university);
//        course.setQuestions(coursePayload.getQuestions());
        courseRepository.save(course);
        return course;
    }

    @Override
    @Transactional
    public List<CourseResponse> getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> responses = courses
                .stream()
                .map(course -> course.mapping())
                .collect(Collectors.toList());
        return responses;
    }
    @Override
    @Transactional
    public CourseResponse getCourseById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course with ID \""+ id + "\" doesn't exist")
        );
        CourseResponse response = course.mapping();
        return response;

    }
    @Override
    @Transactional
    public CourseResponse deleteCourseById(Long id){
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new CourseNotFoundException("Course with ID \""+ id + "\" doesn't exist")
        );
        courseRepository.deleteById(id);
        CourseResponse response = course.mapping();
        return response;
    }

}
