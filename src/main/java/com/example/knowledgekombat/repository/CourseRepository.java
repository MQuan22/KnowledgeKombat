package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c WHERE c.name = :name AND c.status = true")
    Optional<Course> findCourseByName(@Param("name")String courseName);

    @Query("SELECT c FROM Course c WHERE c.user.name = :name AND c.status = true")
    Optional<Course> findCourseByAuthor(@Param("name")String username);

    Optional<List<Course>> findCoursesByCategoryOrderByName(String categoryName);
    Boolean isCourseExist(String courseName);
}
