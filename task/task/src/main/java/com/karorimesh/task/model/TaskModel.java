package com.karorimesh.task.model;

import com.karorimesh.task.enums.Status;
import lombok.Data;

@Data
public class TaskModel {
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Long assigneeId;
    private Status status;
}
