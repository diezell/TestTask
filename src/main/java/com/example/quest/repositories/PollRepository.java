package com.example.quest.repositories;

import com.example.quest.entities.PollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий опросов
 */
@Repository
public interface PollRepository extends JpaRepository<PollEntity, UUID> {

}