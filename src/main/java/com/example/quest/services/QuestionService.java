package com.example.quest.services;

import com.example.quest.dtoQuestion.QuestionRequest;
import com.example.quest.dtoQuestion.QuestionResponse;
import com.example.quest.entities.PollEntity;
import com.example.quest.entities.QuestionEntity;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.repositories.PollRepository;
import com.example.quest.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public QuestionResponse getQuestion(UUID id) throws NotFoundException {
        QuestionEntity entity = questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question was not found"));
        return questionResponseConverter(entity);
    }

    public QuestionResponse createQuestion(QuestionRequest request, UUID id) throws NotFoundException {
        PollEntity pollEntity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Poll was not found"));
        QuestionEntity entity = new QuestionEntity();
        entity.setId(UUID.randomUUID());
        entity.setLink("Ссылка на опрос: " + link + "/poll/" + id);
        entity.setText(request.getText());
        entity.setPollEntity(pollEntity);
        questionRepository.save(entity);
        return questionResponseConverter(entity);
    }





    private QuestionResponse questionResponseConverter(QuestionEntity entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setLink(entity.getLink());
        response.setText(entity.getText());
        response.setResponse(entity.getResponse());
        return response;
    }


}
