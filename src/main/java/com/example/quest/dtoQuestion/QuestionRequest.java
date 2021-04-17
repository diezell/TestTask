package com.example.quest.dtoQuestion;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO-запрос на создание вопроса
 */
@Data
public class QuestionRequest {

    @NotBlank
    private String text;

}
