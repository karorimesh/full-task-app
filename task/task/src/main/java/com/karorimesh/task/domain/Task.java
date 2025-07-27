package com.karorimesh.task.domain;

import com.karorimesh.task.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "user_task")
@SQLRestriction("deleted_at is null")
public class Task extends BaseDomain {
//    Task: id, title, description, status, priority, assigneeId, creatorId,
//    createdAt, updatedAt
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "task_sequence_gen",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence_gen"
    )
    private Long id;
    private String title;
    private String description;
    private Integer priority;
    private Long assigneeId;

    //metadata
    private String assigneeName;
}
