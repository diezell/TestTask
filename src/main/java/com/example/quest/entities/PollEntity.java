package com.example.quest.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность опроса
 */
@Entity
@Data
@Table(name = "poll_table")
public class PollEntity {

    @Id
    private UUID id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private LocalDateTime creationDate;

    private boolean activity;

}