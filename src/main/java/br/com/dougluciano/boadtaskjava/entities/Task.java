package br.com.dougluciano.boadtaskjava.entities;

import br.com.dougluciano.boadtaskjava.enums.TaskPriority;
import br.com.dougluciano.boadtaskjava.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tasks")
public class Task extends AbstractFullEntity{

    @NotBlank(message = "Task title cannot be blank!")
    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @NotNull(message = "Task priority cannot be null!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @NotNull(message = "Task Status cannot be null!")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(name = "due_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
