

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.mapper.ProjectMapper;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectDto createProject(ProjectDto dto) {
        Project project = ProjectMapper.toEntity(dto);

        if (dto.getStudentId() != null) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            project.setStudent(student);
        }

        if (dto.getSupervisorId() != null) {
            User supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new RuntimeException("Supervisor not found"));
            project.setSupervisor(supervisor);
        }

        Project saved = projectRepository.save(project);
        return ProjectMapper.toDTO(saved);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return ProjectMapper.toDTO(project);
    }
}




