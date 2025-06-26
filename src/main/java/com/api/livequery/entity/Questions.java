package com.api.livequery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Questions {

    @Id
    public int questionId;

    public String name;

    public String question;

    public String description;

    @OneToMany
    public List<Comments> comments;
}