package com.example.quest.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Сущность опроса
 */
@Entity
@Data
@Table(name = "poll_table")
public class PollEntity {

    @Id
    @Column(name = "poll_id")
    private UUID id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private LocalDateTime creationDate;

    private boolean activity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pollEntity")
    private List<QuestionEntity> questions;

}