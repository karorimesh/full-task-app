package com.karorimesh.task.services;

import com.karorimesh.task.domain.Task;
import com.karorimesh.task.enums.Status;
import com.karorimesh.task.model.TaskDTO;
import com.karorimesh.task.model.TaskModel;
import com.karorimesh.task.repos.TaskRepository;
import com.karorimesh.task.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
//    - `GET /api/tasks?status=&assignee=`
//            - `POST /api/tasks`
//            - `PUT /api/tasks/{id}`
//            - `DELETE /api/tasks/{id}

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<TaskDTO>> findTasks(Status status, Long id){
        return ResponseEntity.ok(
                taskRepository.findByStatusAndAssigneeId(status, id)
                        .stream().map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList()
        );
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<TaskDTO>> findTasks(Status status){
        return ResponseEntity.ok(
                taskRepository.findByStatus(status)
                        .stream().map(task -> modelMapper.map(task, TaskDTO.class))
                        .toList()
        );
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TaskDTO> add(TaskModel model){
        var task = modelMapper.map(model, Task.class);
        Optional.ofNullable(model.getAssigneeId()).ifPresent(
                val -> task.setAssigneeName(userRepository.findById(val).orElseThrow(
                        () -> new RuntimeException("User not found")
                ).getUsername())
        );
        taskRepository.save(task);
        return ResponseEntity.ok(modelMapper.map(task, TaskDTO.class));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<TaskDTO> update(TaskModel model, Long id){
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );
        Optional.ofNullable(model.getAssigneeId()).ifPresent(
                val -> task.setAssigneeName(userRepository.findById(val).orElseThrow(
                        () -> new RuntimeException("User not found")
                ).getUsername())
        );
        modelMapper.map(model, task);
        taskRepository.save(task);
        return ResponseEntity.ok(modelMapper.map(task, TaskDTO.class));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TaskDTO> delete(Long id){
        var task = taskRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Task not found")
        );
        task.setDeletedAt(LocalDateTime.now());
        taskRepository.save(task);
        return ResponseEntity.ok(modelMapper.map(task, TaskDTO.class));
    }
}
