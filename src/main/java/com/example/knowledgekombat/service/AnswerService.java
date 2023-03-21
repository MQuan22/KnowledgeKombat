package com.example.knowledgekombat.service;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.payload.AnswerRequest;

import java.util.List;

public interface AnswerService {

    public float calculateScore(AnswerRequest request, Long courseId);
}
