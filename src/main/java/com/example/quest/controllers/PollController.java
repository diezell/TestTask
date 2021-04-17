package com.example.quest.controllers;

import com.example.quest.dto.PollRequest;
import com.example.quest.dto.PollResponse;
import com.example.quest.dto.PollsResponse;
import com.example.quest.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poll")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public PollsResponse getPolls() {
        return pollService.getPolls();
    }

    @PostMapping
    public PollResponse createPoll(@RequestBody PollRequest request) {
        return pollService.createPoll(request);
    }







}
