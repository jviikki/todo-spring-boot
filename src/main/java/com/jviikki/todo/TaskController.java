package com.jviikki.todo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/tasks")
    public Task getTask(@RequestParam(value="name", defaultValue="default") String name) {
        return new Task(counter.incrementAndGet(), name);
    }
}
