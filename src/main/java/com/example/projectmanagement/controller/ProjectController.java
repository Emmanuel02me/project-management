


package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectDto createProject(@Valid @RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }
}



