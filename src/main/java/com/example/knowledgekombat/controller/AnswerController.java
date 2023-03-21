package com.example.knowledgekombat.controller;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.payload.AnswerRequest;
import com.example.knowledgekombat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/result/{courseId}")
    public float calculateScore(@RequestBody AnswerRequest request, @PathVariable(name="courseId") Long courseId){
        float result = answerService.calculateScore(request, courseId);
        return result;
    }
}
