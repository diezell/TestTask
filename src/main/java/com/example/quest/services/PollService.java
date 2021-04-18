package com.example.quest.services;

import com.example.quest.dtoPoll.*;
import com.example.quest.entities.*;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис опросов
 */
@Service
public class PollService {

    private final PollRepository pollRepository;

    private final QuestionRepository questionRepository;

    @Autowired
    public PollService(PollRepository pollRepository, QuestionRepository questionRepository) {
        this.pollRepository = pollRepository;
        this.questionRepository = questionRepository;
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

    public PollsResponse getPolls(String filter, String sort) {
        List<PollEntity> listOfEntities = pollRepository.findAll();
        PollsResponse pollsResponse = new PollsResponse();
        List<PollResponse> listOfResponses = new ArrayList<>();
        listOfEntities.forEach(poll -> listOfResponses.add(pollResponseConverter(poll)));
        List<PollResponse> list = listOfResponses;
        if (filter != null) {
            switch (filter) {
                case "active":
                    list = list.stream().filter(PollResponse::isActivity).collect(Collectors.toList());
                    break;
                case "notActive":
                    list = listOfResponses.stream().filter(poll -> !poll.isActivity()).collect(Collectors.toList());
                    break;
            }
        }
        if (sort != null) {
            switch (sort) {
                case "startDate":
                    list.sort(Comparator.comparing(PollResponse::getStartDate));
                    break;
                case "startDateReverse":
                    list.sort(Comparator.comparing(PollResponse::getStartDate));
                    Collections.reverse(list);
                    break;
                case "name":
                    list.sort(Comparator.comparing(PollResponse::getName));
                    break;
                case "nameReverse":
                    list.sort(Comparator.comparing(PollResponse::getName));
                    Collections.reverse(list);
                    break;
            }
        }
        pollsResponse.setPolls(list);
        return pollsResponse;
    }

    public PollResponse getPoll(UUID id) throws NotFoundException {
        PollEntity entity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Poll was not found"));
        return pollResponseConverter(entity);
    }

    public PollResponse changePoll(PollChangeRequest request, UUID id) throws NotFoundException {
        PollEntity entity = pollRepository.findById(id).orElseThrow(() -> new NotFoundException("Poll was not found"));
        if (!request.getName().isEmpty()) {
            entity.setName(request.getName());
        }
        if (request.getStartDate() != null) {
            entity.setStartDate(request.getStartDate());
        }
        if (request.getFinishDate() != null) {
            entity.setFinishDate(request.getFinishDate());
        }
        pollRepository.save(entity);
        return pollResponseConverter(entity);
    }

    public void deletePoll(UUID id) throws NotFoundException {
        List<QuestionEntity> allQuestions = questionRepository.findAllByPollEntityId(id);
        allQuestions.forEach(question -> questionRepository.deleteById(question.getId()));
        if (!pollRepository.existsById(id)) {
            throw new NotFoundException("Poll was not found");
        } else pollRepository.deleteById(id);
    }

    private PollResponse pollResponseConverter(PollEntity entity) {
        PollResponse response = new PollResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setStartDate(entity.getStartDate());
        response.setFinishDate(entity.getFinishDate());
        response.setCreationDate(entity.getCreationDate());
        response.setActivity(isActivity(entity));
        return response;
    }

    private Boolean isActivity(PollEntity entity) {
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
