package com.jviikki.todo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class TaskData {
    private String name;
    private Date createdAt;

    public TaskData(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("CreatedAt")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
