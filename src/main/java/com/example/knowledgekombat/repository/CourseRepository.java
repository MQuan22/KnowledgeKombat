package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("SELECT c.questions FROM Course c WHERE c.id = ?1")
    List<Question> findQuestionByCourseId(Long courseId);

    Optional<Course> findById(Long CourseId);
    @Query("SELECT c FROM Course c")
    List<Course> findAll();
}
