package com.jviikki.todo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/api/tasks/{id}", method = RequestMethod.GET)
    public Task getTask(@PathVariable("id") long id) throws TaskService.TaskNotFoundException {
        return taskService.getTask(id);
    }

    @RequestMapping(value = "/api/tasks", method = RequestMethod.POST)
    public long createTask(@RequestBody TaskData taskData) {
        Task t = new Task(0, taskData.getName(), taskData.getCreatedAt());
        taskService.createTask(t);
        return t.getId();
    }

    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleAppException(TaskService.TaskNotFoundException e) {
        return "Task not found";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAppException(Exception e) {
        return e.getMessage();
    }

}
