package com.api.livequery.repository;

import com.api.livequery.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CommentsRepository provides CRUD operations for Comments entity.
 * It extends JpaRepository to leverage Spring Data JPA features.
 */
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}