package com.karorimesh.task.resource;

import com.karorimesh.task.enums.Status;
import com.karorimesh.task.model.TaskDTO;
import com.karorimesh.task.model.TaskModel;
import com.karorimesh.task.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTasks(@RequestParam("status")Status status, @RequestParam("assignee") Long id){
        return taskService.findTasks(status, id);
    }

    @GetMapping("grouped/{status}")
    public ResponseEntity<List<TaskDTO>> findGroupedTasks(@PathVariable("status") Status status){
        return taskService.findTasks(status);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskModel taskModel){
        return taskService.add(taskModel);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskModel taskModel, @PathVariable Long id){
        return taskService.update(taskModel, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id){
        return taskService.delete(id);
    }
}
