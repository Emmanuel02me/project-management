// tar 28 new

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.AssignmentRequest;
import com.example.projectmanagement.dto.StudentSupervisorDto;
import com.example.projectmanagement.service.SupervisorAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

// works fine

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class SupervisorAssignmentController {

    private final SupervisorAssignmentService service;

    @PostMapping
    public ResponseEntity<StudentSupervisorDto> assignStudentToSupervisor(
            @RequestParam Long studentId,
            @RequestParam Long supervisorId) {
        return ResponseEntity.ok(service.assignStudentToSupervisor(studentId, supervisorId));
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<List<StudentSupervisorDto>> getStudentsBySupervisor(@PathVariable Long supervisorId) {
        return ResponseEntity.ok(service.getStudentsBySupervisor(supervisorId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentSupervisorDto>> getSupervisorsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getSupervisorsByStudent(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentSupervisorDto>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }
}

 */

//an update

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class SupervisorAssignmentController {

    private final SupervisorAssignmentService service;

    // Existing single assignment
    @PostMapping
    public ResponseEntity<StudentSupervisorDto> assignStudentToSupervisor(
            @RequestParam Long studentId,
            @RequestParam Long supervisorId) {
        return ResponseEntity.ok(service.assignStudentToSupervisor(studentId, supervisorId));
    }

    // NEW: Bulk assignment
    @PostMapping("/bulk")
    public ResponseEntity<List<StudentSupervisorDto>> assignStudentsToSupervisor(
            @RequestBody AssignmentRequest request) {
        return ResponseEntity.ok(
                service.assignStudentsToSupervisor(request.getStudentIds(), request.getSupervisorId())
        );
    }

    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<List<StudentSupervisorDto>> getStudentsBySupervisor(@PathVariable Long supervisorId) {
        return ResponseEntity.ok(service.getStudentsBySupervisor(supervisorId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentSupervisorDto>> getSupervisorsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getSupervisorsByStudent(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentSupervisorDto>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }
}
