package com.example.quest.services;

import com.example.quest.dtoQuestion.*;
import com.example.quest.entities.*;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Сервис вопросов
 */
@Service
public class QuestionService {

    @Value("${poll.link}")
    private String link;

    private final PollRepository pollRepository;

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(PollRepository pollRepository, QuestionRepository questionRepository) {
        this.pollRepository = pollRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionsResponse getQuestions(UUID id) {
        List<QuestionEntity> listOfEntities = questionRepository.findAllByPollEntityId(id);
        QuestionsResponse questionsResponse = new QuestionsResponse();
        List<QuestionResponse> listOfQuestions = new ArrayList<>();
        listOfEntities.forEach(list -> listOfQuestions.add(questionResponseConverter(list)));
        questionsResponse.setQuestions(listOfQuestions);
        return questionsResponse;
    }

    public QuestionResponse getQuestion(UUID id) throws NotFoundException {
        QuestionEntity entity = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question was not found"));
        return questionResponseConverter(entity);
    }

    public QuestionResponse createQuestion(QuestionRequest request, UUID id) throws NotFoundException {
        PollEntity pollEntity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Poll was not found"));
        QuestionEntity entity = new QuestionEntity();
        entity.setId(UUID.randomUUID());
        entity.setText(request.getText());
        entity.setPollEntity(pollEntity);
        questionRepository.save(entity);
        return questionResponseConverter(entity);
    }

    public QuestionResponse answer(AnswerRequest request, UUID id) throws NotFoundException {
        QuestionEntity entity = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question was not found"));
        entity.setResponse(request.getResponse());
        questionRepository.save(entity);
        return questionResponseConverter(entity);
    }

    public void deleteQuestion(UUID id) {
        questionRepository.deleteById(id);
    }

    private QuestionResponse questionResponseConverter(QuestionEntity entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setLink("Ссылка на опрос: " + link + "/poll/" + entity.getPollEntity().getId());
        response.setText(entity.getText());
        response.setResponse(entity.getResponse());
        return response;
    }

}
