package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
