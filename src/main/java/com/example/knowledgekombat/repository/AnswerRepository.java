package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT q FROM Answer q WHERE q.id = ?1")
    Optional<Answer> findById(Long Id);
    @Query("SELECT COUNT (a) FROM Answer a WHERE a.question.course.id =?1 AND a.isCorrect = true")
    int countTotal(Long courseId);


}
