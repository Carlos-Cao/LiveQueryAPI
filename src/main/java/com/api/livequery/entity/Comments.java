package com.api.livequery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Comments entity represents a comment in the system.
 * It contains fields for comment ID, name, comment text, and associated question ID.
 */
@Entity
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Comment is required")
    private String comment;

    @NotNull(message = "Question ID is required")
    @Column(name = "question_id")
    private int questionId;
}