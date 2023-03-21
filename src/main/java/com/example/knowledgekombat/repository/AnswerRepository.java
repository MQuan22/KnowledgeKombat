package com.example.knowledgekombat.repository;

import com.example.knowledgekombat.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    @Override
    Optional<Answer> findById(Long answerId);
}
