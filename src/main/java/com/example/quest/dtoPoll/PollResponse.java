package com.example.quest.dtoPoll;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO-ответ на получение опроса
 */
@Data
public class PollResponse {

    private UUID id;

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finishDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    private boolean activity;

}
