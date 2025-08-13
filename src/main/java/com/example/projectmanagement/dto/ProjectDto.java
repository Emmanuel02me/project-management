
package com.example.projectmanagement.dto;

import com.example.projectmanagement.model.Project.ProjectStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDto {

    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @NotNull(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    private String description;

    @NotNull(message = "Status is required")
    private ProjectStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Supervisor ID is required")
    private Long supervisorId;
}




