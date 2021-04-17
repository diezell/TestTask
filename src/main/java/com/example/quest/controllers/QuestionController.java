package com.example.quest.controllers;

import com.example.quest.dtoPoll.PollChangeRequest;
import com.example.quest.dtoQuestion.QuestionRequest;
import com.example.quest.dtoQuestion.QuestionResponse;
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

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

//    @GetMapping("/poll/{id}")
//    public ;

    @GetMapping("/{id}")
    public QuestionResponse getQuestion(@PathVariable("id") UUID id) throws NotFoundException {
        return questionService.getQuestion(id);
    }



    @PostMapping("/{id}")
    public QuestionResponse createQuestion(@RequestBody @Valid QuestionRequest request,
                                           @PathVariable("id") UUID id) throws NotFoundException {
        return questionService.createQuestion(request, id);
    }




}
