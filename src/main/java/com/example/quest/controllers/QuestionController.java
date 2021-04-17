package com.example.quest.controllers;

import com.example.quest.dtoQuestion.AnswerRequest;
import com.example.quest.dtoQuestion.QuestionRequest;
import com.example.quest.dtoQuestion.QuestionResponse;
import com.example.quest.dtoQuestion.QuestionsResponse;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер вопросов
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    /**
     * @param questionService - сервис вопросов
     */
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * GET-запрос на получение всех вопросов определенного опроса
     * @param id - id опроса
     * @return - возвращает список вопросов
     */
    @GetMapping("/poll/{id}")
    public QuestionsResponse getQuestions(@PathVariable("id") UUID id) {
        return questionService.getQuestions(id);
    }

    /**
     * GET-запрос на получение вопроса
     * @param id - id нужного вопроса
     * @return - возвращает найденный вопрос
     */
    @GetMapping("/{id}")
    public QuestionResponse getQuestion(@PathVariable("id") UUID id) throws NotFoundException {
        return questionService.getQuestion(id);
    }

    /**
     * POST-запрос на создание вопроса
     * @param request - тело запроса с параметрами
     * @param id - id опроса, в котором создается вопрос
     * @return - возвращает созданный вопрос
     */
    @PostMapping("/{id}")
    public QuestionResponse createQuestion(@RequestBody @Valid QuestionRequest request,
                                           @PathVariable("id") UUID id) throws NotFoundException {
        return questionService.createQuestion(request, id);
    }

    /**
     * PUT-запрос с ответом на вопрос
     * @param request - тело запроса с ответом
     * @param id - id вопроса
     * @return - возвращает иземененный вопрос
     */
    @PutMapping("/{id}")
    public QuestionResponse answer(@RequestBody @Valid AnswerRequest request,
                                   @PathVariable("id") UUID id) throws NotFoundException {
        return questionService.answer(request, id);
    }

    /**
     * DELETE-запрос на удаление вопроса
     * @param id - id удаляемого вопроса
     */
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable("id") UUID id) {
        questionService.deleteQuestion(id);
    }

}
