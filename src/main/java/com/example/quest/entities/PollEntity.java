package com.example.quest.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class PollEntity {

    @Id
    private UUID id;

    private String name;

    private LocalDateTime startDate;

}
