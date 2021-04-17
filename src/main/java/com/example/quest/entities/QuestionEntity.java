package com.example.quest.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность вопроса
 */
@Entity
@Data
@Table(name = "question_table")
public class QuestionEntity {

    @Id
    private UUID id;

    private String text;

    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private PollEntity pollEntity;

}
