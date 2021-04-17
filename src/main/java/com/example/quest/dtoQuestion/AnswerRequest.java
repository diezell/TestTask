package com.example.quest.dtoQuestion;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * DTO-запрос с ответом на вопрос
 */
@Data
public class AnswerRequest {

    @NotBlank
    private String response;
    
}
