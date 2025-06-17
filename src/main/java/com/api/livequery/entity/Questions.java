package com.api.livequery.entity;

import lombok.Data;

import java.util.List;

@Data
public class Questions {

    public int questionId;

    public String name;

    public String question;

    public String description;

    public List<Comments> comments;
}