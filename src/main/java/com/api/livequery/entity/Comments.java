package com.api.livequery.entity;

import lombok.Data;

@Data
public class Comments {

    public int commentId;

    public String name;

    public String comment;

    public int questionId;
}