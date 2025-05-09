package com.coderj001.taskmanager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final Map<Long, Task> tasks = new HashMap<>();
    AtomicLong counter = new AtomicLong(0);

    public Map<Long, Task> getTasks() {
        return tasks;
    }

    public AtomicLong getCounter() {
        return counter;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask() {
        return new ResponseEntity<>(new ArrayList<>(tasks.values()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@org.jetbrains.annotations.NotNull @RequestBody Task task) {
        long id = counter.incrementAndGet();
        task.setID(id);
        tasks.put(id, task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task updatedTask) {
        if (tasks.containsKey(id)) {
            updatedTask.setID(id);
            tasks.put(id, updatedTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            tasks.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
