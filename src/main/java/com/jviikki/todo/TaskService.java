package com.jviikki.todo;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private static final ConcurrentMap<Long, Task> todoList = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong();

    public void createTask(Task task) {
        task.setId(currentId.incrementAndGet());
        this.todoList.put(new Long(task.getId()), task);
    }

    public Task getTask(long id) {
        return this.todoList.get(new Long(id));
    }
}
