package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.model.Project;

public class ProjectMapper {

    public static ProjectDto toDTO(Project project) {
        if (project == null) return null;

        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setStatus(project.getStatus());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());

        if (project.getStudent() != null) {
            dto.setStudentId(project.getStudent().getId());
        }
        if (project.getSupervisor() != null) {
            dto.setSupervisorId(project.getSupervisor().getId());
        }

        return dto;
    }

    public static Project toEntity(ProjectDto dto) {
        if (dto == null) return null;

        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setStatus(dto.getStatus());

        // createdAt and updatedAt are managed by @PrePersist/@PreUpdate

        // Student and Supervisor entities to be set in service from IDs

        return project;
    }
}
