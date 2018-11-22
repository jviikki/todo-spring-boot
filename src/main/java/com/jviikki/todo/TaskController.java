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
    public void createTask(@RequestBody TaskData taskData) {
        taskService.createTask(new Task(0, taskData.getName(), taskData.getCreatedAt()));
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
