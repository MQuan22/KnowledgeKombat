package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<User_Course, Long> {
    Boolean existsByCourseIdAndUserId(Long courseId, Long userId);
    @Query("SELECT DISTINCT uc FROM User_Course uc")
    List<User_Course> getAllDistinct();
}
