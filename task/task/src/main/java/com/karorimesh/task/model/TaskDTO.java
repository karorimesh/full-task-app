package com.karorimesh.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends BaseDTO{
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Long assigneeId;

    //metadata
    private String assigneeName;
}
