package com.api.livequery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Comments {

    @Id
    public int commentId;

    public String name;

    public String comment;

    public int questionId;
}