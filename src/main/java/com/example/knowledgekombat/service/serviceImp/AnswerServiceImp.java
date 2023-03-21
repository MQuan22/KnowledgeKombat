package com.example.knowledgekombat.service.serviceImp;

import com.example.knowledgekombat.model.Answer;
import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.repository.AnswerRepository;
import com.example.knowledgekombat.repository.CourseRepository;
import com.example.knowledgekombat.repository.QuestionRepository;
import com.example.knowledgekombat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImp(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public float calculateScore(List<Long> answers) {
        float result = 0;
        for (Long id: answers) {
            Answer answer = answerRepository.getById(id);
            if(answer.isCorrect()){
                result += 1;
            }
        }
        return result;
    }
}
