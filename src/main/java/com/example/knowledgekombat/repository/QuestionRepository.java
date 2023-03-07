package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q.answers FROM Question q WHERE q.id = ?1")
    List<Answer> findAnswersByQuestionId(Long courseId);
}
