package com.example.quest.services;

import com.example.quest.dtoPoll.PollChangeRequest;
import com.example.quest.dtoPoll.PollRequest;
import com.example.quest.dtoPoll.PollResponse;
import com.example.quest.dtoPoll.PollsResponse;
import com.example.quest.entities.PollEntity;
import com.example.quest.entities.QuestionEntity;
import com.example.quest.exceptions.NotFoundException;
import com.example.quest.repositories.PollRepository;
import com.example.quest.repositories.QuestionRepository;
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
            if (filter.equals("active")) {
                list = list.stream().filter(PollResponse::isActivity).collect(Collectors.toList());
            } else if (filter.equals("notActive")) {
                list = listOfResponses.stream().filter(poll -> !poll.isActivity()).collect(Collectors.toList());
            }
        }
        if (sort != null) {
            if (sort.equals("startDate")) {
                list.sort(Comparator.comparing(PollResponse::getStartDate));
            } else if (sort.equals("startDateReverse")) {
                list.sort(Comparator.comparing(PollResponse::getStartDate));
                Collections.reverse(list);
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

    public void deletePoll(UUID id) {
        List<QuestionEntity> allQuestions = questionRepository.findAllByPollEntityId(id);
        allQuestions.forEach(question -> questionRepository.deleteById(question.getId()));
        pollRepository.deleteById(id);
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
