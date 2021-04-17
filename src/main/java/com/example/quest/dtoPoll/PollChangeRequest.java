package com.example.quest.dtoPoll;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO-запрос на изменение опроса
 */
@Data
public class PollChangeRequest {

    @Size(max = 40)
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime finishDate;

}
