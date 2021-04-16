package com.example.quest.services;

import com.example.quest.dto.PollRequest;
import com.example.quest.dto.PollResponse;
import com.example.quest.entities.PollEntity;
import com.example.quest.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public PollResponse createPoll(PollRequest request) {
        PollEntity newPoll = new PollEntity();
        newPoll.setId(UUID.randomUUID());
        newPoll.setName(request.getName());
        newPoll.setStartDate(request.getStartDate());
        newPoll.setFinishDate(request.getFinishDate());
        newPoll.setCreationDate(LocalDateTime.now());
        pollRepository.save(newPoll);
        return pollResponseConverter(newPoll);
    }





    private PollResponse pollResponseConverter(PollEntity entity) {
        PollResponse response = new PollResponse();
        response.setName(entity.getName());
        response.setStartDate(entity.getStartDate());
        response.setFinishDate(entity.getFinishDate());
        response.setCreationDate(entity.getCreationDate());
        return response;
    }

    private Boolean isActivity(LocalDateTime startDate, LocalDateTime finishDate) {
        return startDate.isBefore(LocalDateTime.now()) && finishDate.isAfter(LocalDateTime.now());
    }


}
