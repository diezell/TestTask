package com.example.quest.dtoPoll;

import lombok.Data;

import java.util.List;

/**
 * DTO-ответ на получение списка опросов
 */
@Data
public class PollsResponse {

    private List<PollResponse> polls;

}
