package com.example.knowledgekombat.service.serviceImp;

import com.example.knowledgekombat.exception.CourseNotFoundException;
import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.User;
import com.example.knowledgekombat.model.User_Course;
import com.example.knowledgekombat.payload.AnswerRequest;
import com.example.knowledgekombat.repository.*;
import com.example.knowledgekombat.security.UserPrincipal;
import com.example.knowledgekombat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService {
    private final AnswerRepository answerRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserCourseRepository userCourseRepository;
    @Autowired
    public AnswerServiceImp(AnswerRepository answerRepository, CourseRepository courseRepository, UserRepository userRepository, UserCourseRepository userCourseRepository) {
        this.answerRepository = answerRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    @Transactional
    public float calculateScore(AnswerRequest request, Long courseId) {
        float result = 0;
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userRepository.findById(userPrincipal.getId()).orElseThrow(
                () -> new UsernameNotFoundException("Unauthorized"));
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseNotFoundException("Course with ID \""+ courseId + "\" doesn't exist")
        );
        for (Long id: request.getAnswerList()) {
            Answer answer = answerRepository.getById(id);
            if(answer.getQuestion().getCourse().getId() != courseId)
                throw new RuntimeException("Answer is not in course");
            if(answer.isCorrect()){
                result += 1;
            }
        }
        Long total = answerRepository.countTotal(courseId);
        float score = result/total*10;
        float roundedScore = (float) Math.round(score * 100) / 100;
        if(userCourseRepository.existsByCourseIdAndUserId(courseId, user.getId()) == false) {
            Date date = new Date();
            User_Course user_course = new User_Course();
            user_course.setCourse(course);
            user_course.setUser(user);
            user_course.setScore(roundedScore);
            user_course.setUpdatedAt(date);
            List<User_Course> userCourses = course.getUserCourses();
            userCourses.add(user_course);
            course.setUserCourses(userCourses);
            courseRepository.save(course);
        }
        return roundedScore;
    }
}
