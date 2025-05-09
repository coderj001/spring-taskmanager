package com.coderj001.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    public TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Tasks>> getAllTask() {
        return new ResponseEntity<>(new ArrayList<>(taskRepository.findAll()), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Task> createTask(@org.jetbrains.annotations.NotNull @RequestBody Task task) {
//        return new ResponseEntity<>(task, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//        Task task = tasks.get(id);
//        if (task != null) {
//            return new ResponseEntity<>(task, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Task> updateTaskById(@PathVariable Long id, @RequestBody Task updatedTask) {
//        if (tasks.containsKey(id)) {
//            updatedTask.setID(id);
//            tasks.put(id, updatedTask);
//            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
//        Task task = tasks.get(id);
//        if (task != null) {
//            tasks.remove(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
