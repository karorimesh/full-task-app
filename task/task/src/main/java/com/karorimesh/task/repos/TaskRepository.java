package com.karorimesh.task.repos;

import com.karorimesh.task.domain.Task;
import com.karorimesh.task.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusAndAssigneeId(Status status, Long id);
    List<Task> findByStatus(Status status);
}
