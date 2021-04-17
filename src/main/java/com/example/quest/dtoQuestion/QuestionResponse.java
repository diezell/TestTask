package com.example.quest.dtoQuestion;

import lombok.Data;

import java.util.UUID;

/**
 * DTO-ответ на получение вопроса
 */
@Data
public class QuestionResponse {

    private UUID id;

    private String link;

    private String text;

    private String response;

}
