package com.example.quest.services;

import com.example.quest.dto.PollRequest;
import com.example.quest.dto.PollResponse;
import com.example.quest.dto.PollsResponse;
import com.example.quest.entities.PollEntity;
import com.example.quest.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        newPoll.setActivity(isActivity(newPoll));
        pollRepository.save(newPoll);
        return pollResponseConverter(newPoll);
    }

    public PollsResponse getPolls() {
        List<PollEntity> listOfEntities = pollRepository.findAll();
        PollsResponse pollsResponse = new PollsResponse();
        List<PollResponse> listOfResponses = new ArrayList<>();
        listOfEntities.forEach(list -> listOfResponses.add(pollResponseConverter(list)));

        pollsResponse.setPolls(listOfResponses);
        return pollsResponse;

    }





    private PollResponse pollResponseConverter(PollEntity entity) {
        PollResponse response = new PollResponse();
        response.setName(entity.getName());
        response.setStartDate(entity.getStartDate());
        response.setFinishDate(entity.getFinishDate());
        response.setCreationDate(entity.getCreationDate());
        response.setActivity(isActivity(entity));
        return response;
    }

    private Boolean isActivity(PollEntity entity) {   //todo: refactoring
        boolean isAct = entity.getStartDate().isBefore(LocalDateTime.now())
                && entity.getFinishDate().isAfter(LocalDateTime.now());
        if (pollRepository.existsById(entity.getId())) {
            Optional<PollEntity> pollEntity = pollRepository.findById(entity.getId());
            pollEntity.get().setActivity(isAct);
            pollRepository.save(pollEntity.get());
        }
        return isAct;
    }

}
