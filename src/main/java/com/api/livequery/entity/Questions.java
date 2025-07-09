package com.api.livequery.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * Questions entity represents a question in the system.
 * It contains fields for question ID, name, question, description, and a list of associated comments.
 */
@Entity
@Data
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Question is required")
    private String question;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Comments> comments;
}