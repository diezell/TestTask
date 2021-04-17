package com.example.quest.repositories;

import com.example.quest.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий вопросов
 */
@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
}
