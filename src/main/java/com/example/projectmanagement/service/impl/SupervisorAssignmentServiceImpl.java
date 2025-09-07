
// this is new tar 28

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.StudentSupervisorDto;
import com.example.projectmanagement.model.StudentSupervisor;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.StudentSupervisorRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.SupervisorAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
//works fine
@Service
@RequiredArgsConstructor
public class SupervisorAssignmentServiceImpl implements SupervisorAssignmentService {

    private final StudentSupervisorRepository repository;
    private final UserRepository userRepository;

    @Override
    public StudentSupervisorDto assignStudentToSupervisor(Long studentId, Long supervisorId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        // prevent duplicate assignment
        boolean exists = repository.findByStudentId(studentId)
                .stream()
                .anyMatch(a -> a.getSupervisor().getId().equals(supervisorId));
        if (exists) {
            throw new RuntimeException("This student is already assigned to this supervisor.");
        }

        StudentSupervisor assignment = StudentSupervisor.builder()
                .student(student)
                .supervisor(supervisor)
                .build();

        return mapToDto(repository.save(assignment));
    }

    @Override
    public List<StudentSupervisorDto> getStudentsBySupervisor(Long supervisorId) {
        return repository.findBySupervisorId(supervisorId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSupervisorDto> getSupervisorsByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSupervisorDto> getAllAssignments() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private StudentSupervisorDto mapToDto(StudentSupervisor entity) {
        return StudentSupervisorDto.builder()
                .id(entity.getId())
                .studentId(entity.getStudent().getId())
                .studentName(entity.getStudent().getName())
                .studentEmail(entity.getStudent().getEmail())
                .projectTitle(entity.getStudent().getProjectTitle())
                .supervisorId(entity.getSupervisor().getId())
                .supervisorName(entity.getSupervisor().getName())
                .supervisorEmail(entity.getSupervisor().getEmail())
                .build();
    }
}

 */

// an update for bulk assignment

@Service
@RequiredArgsConstructor
public class SupervisorAssignmentServiceImpl implements SupervisorAssignmentService {

    private final StudentSupervisorRepository repository;
    private final UserRepository userRepository;

    @Override
    public StudentSupervisorDto assignStudentToSupervisor(Long studentId, Long supervisorId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        User supervisor = userRepository.findById(supervisorId)
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        boolean exists = repository.findByStudentId(studentId)
                .stream()
                .anyMatch(a -> a.getSupervisor().getId().equals(supervisorId));
        if (exists) {
            throw new RuntimeException("This student is already assigned to this supervisor.");
        }

        StudentSupervisor assignment = StudentSupervisor.builder()
                .student(student)
                .supervisor(supervisor)
                .build();

        return mapToDto(repository.save(assignment));
    }

    // NEW: Bulk assignment
    @Override
    public List<StudentSupervisorDto> assignStudentsToSupervisor(List<Long> studentIds, Long supervisorId) {
        return studentIds.stream()
                .map(studentId -> assignStudentToSupervisor(studentId, supervisorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSupervisorDto> getStudentsBySupervisor(Long supervisorId) {
        return repository.findBySupervisorId(supervisorId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSupervisorDto> getSupervisorsByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentSupervisorDto> getAllAssignments() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private StudentSupervisorDto mapToDto(StudentSupervisor entity) {
        return StudentSupervisorDto.builder()
                .id(entity.getId())
                .studentId(entity.getStudent().getId())
                .studentName(entity.getStudent().getName())
                .studentEmail(entity.getStudent().getEmail())
                .studentProgram(entity.getStudent().getProgram())
                .projectTitle(entity.getStudent().getProjectTitle())
                .supervisorId(entity.getSupervisor().getId())
                .supervisorName(entity.getSupervisor().getName())
                .supervisorEmail(entity.getSupervisor().getEmail())
                .build();
    }
}

