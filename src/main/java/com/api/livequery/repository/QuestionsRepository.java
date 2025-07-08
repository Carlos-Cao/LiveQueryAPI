package com.api.livequery.repository;

import com.api.livequery.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * QuestionsRepository provides CRUD operations for Questions entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
}