package com.example.quest.controllers;

import com.example.quest.dtoPoll.*;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Контроллер опросов
 */
@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollService pollService;

    /**
     * @param pollService - сервис опросов
     */
    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    /**
     * GET-запрос на получение всех опросов
     * @param filter - фильтрация опросов (необязательный параметр)
     * @param sort - сортировка опросов (необязательный параметр)
     * @return - возвращает список опросов
     */
    @GetMapping
    public PollsResponse getPolls(@RequestParam(value = "filter", required = false) String filter,
                                  @RequestParam(value = "sort", required = false) String sort) {
        return pollService.getPolls(filter, sort);
    }

    /**
     * GET-запрос на получение опроса
     * @param id - id нужного опроса
     * @return - возвращает найденный опрос
     */
    @GetMapping("/{id}")
    public PollResponse getPoll(@PathVariable("id") UUID id) throws NotFoundException {
        return pollService.getPoll(id);
    }

    /**
     * POST-запрос на создание опроса
     * @param request - тело запроса с параметрами
     * @return - возвращает созданный опрос
     */
    @PostMapping
    public PollResponse createPoll(@RequestBody @Valid PollRequest request) {
        return pollService.createPoll(request);
    }

    /**
     * PUT-запрос на изменение опроса
     * @param request - тело запроса с параметрами
     * @param id - id изменяемого опроса
     * @return - возвращает иземененный опрос
     */
    @PutMapping("/{id}")
    public PollResponse changePoll(@RequestBody @Valid PollChangeRequest request,
                                   @PathVariable("id") UUID id) throws NotFoundException {
        return pollService.changePoll(request, id);
    }

    /**
     * DELETE-запрос на удаление опроса и всех связанных вопросов
     * @param id - id удаляемого опроса
     */
    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable("id") UUID id) throws NotFoundException {
        pollService.deletePoll(id);
    }

}
