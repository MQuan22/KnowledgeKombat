package com.example.knowledgekombat.service;

import com.example.knowledgekombat.model.Answer;

import java.util.List;

public interface AnswerService {

    public float calculateScore(List<Long> answerIDs);
}
