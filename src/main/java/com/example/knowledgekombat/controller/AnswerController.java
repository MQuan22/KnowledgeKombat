package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/result")
    public float calculateScore(@RequestBody List<Long> answerList){
        float result = answerService.calculateScore(answerList);
        return result;
    }
}
