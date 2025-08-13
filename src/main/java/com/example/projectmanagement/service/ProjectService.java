

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);
    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(Long id);
}




