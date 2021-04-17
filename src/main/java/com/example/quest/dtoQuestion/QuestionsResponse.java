package com.example.quest.dtoQuestion;

import lombok.Data;

import java.util.List;

/**
 * DTO-ответ на получение всех вопросов определенного опроса
 */
@Data
public class QuestionsResponse {

    private List<QuestionResponse> questions;

}
