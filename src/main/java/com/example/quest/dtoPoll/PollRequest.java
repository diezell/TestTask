package com.example.quest.dtoPoll;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * DTO-запрос на создание опроса
 */
@Data
public class PollRequest {

    @NotBlank
    @Size(max = 40)
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finishDate;

}
