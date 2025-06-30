package com.api.livequery.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int commentId;

    public String name;

    public String comment;

    @Column(name = "question_id")
    public int questionId;
}