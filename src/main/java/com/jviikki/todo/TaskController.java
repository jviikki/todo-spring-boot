package com.jviikki.todo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/api/tasks/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable("id") long id) {
        return taskService.getTask(id);
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
    public void createTask(@RequestBody TaskData taskData) {
        taskService.createTask(new Task(0, taskData.getName(), taskData.getCreatedAt()));
    }
}
