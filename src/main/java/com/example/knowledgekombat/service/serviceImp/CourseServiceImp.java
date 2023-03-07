package com.example.knowledgekombat.service.serviceImp;

import com.example.knowledgekombat.model.*;
import com.example.knowledgekombat.payload.CoursePayload;
import com.example.knowledgekombat.repository.CategoryRepository;
import com.example.knowledgekombat.repository.CourseRepository;
import com.example.knowledgekombat.repository.UniRepository;
import com.example.knowledgekombat.repository.UserRepository;
import com.example.knowledgekombat.security.UserPrincipal;
import com.example.knowledgekombat.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UniRepository uniRepository;
    @Autowired
    public CourseServiceImp(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository, UniRepository uniRepository ){
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.uniRepository = uniRepository;
    }

    @Override
    @Transactional
    public Course createCourse(CoursePayload coursePayload) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User author = userRepository.findById(userPrincipal.getId()).orElseThrow(
                () -> new UsernameNotFoundException("Unauthorized admin"));
        Category category = categoryRepository.findByName(coursePayload.getCategory()).get();
        University university = uniRepository.findByName(coursePayload.getUniversity()).get();
        Course course = new Course();
        course.setName(coursePayload.getName());
        course.setCategory(category);
        course.setStatus(coursePayload.isStatus());
        course.setDescription(coursePayload.getDescription());
        course.setImage(coursePayload.getImage());
        course.setUser(author);
        course.setUniversity(university);
        courseRepository.save(course);
        course.setTopics(coursePayload.getTopics());
        return course;
    }
}
