package com.jviikki.todo;

import java.util.Date;

public class Task {

    private final long id;
    private final String name;
    private final Date createdAt;

    public Task(long id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = new Date();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
