package com.jviikki.todo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private static final ConcurrentMap<Long, Task> todoList = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong();

    // @ResponseStatus(HttpStatus.NOT_FOUND)
    public class TaskNotFoundException extends Exception {
    }

    public void createTask(Task task) {
        task.setId(currentId.incrementAndGet());
        this.todoList.put(new Long(task.getId()), task);
    }

    public Task getTask(long id) throws TaskNotFoundException {
        Task t = this.todoList.get(new Long(id));
        if (t == null) throw new TaskNotFoundException();
        return t;
    }
}
