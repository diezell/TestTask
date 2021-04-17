package com.example.quest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PollResponse {

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finishDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creationDate;

    private boolean activity;

}
