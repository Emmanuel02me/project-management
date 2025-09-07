//new tar 28

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.StudentSupervisorDto;

import java.util.List;

/*

//this works fine

public interface SupervisorAssignmentService {
    StudentSupervisorDto assignStudentToSupervisor(Long studentId, Long supervisorId);
    List<StudentSupervisorDto> getStudentsBySupervisor(Long supervisorId);
    List<StudentSupervisorDto> getSupervisorsByStudent(Long studentId);
    List<StudentSupervisorDto> getAllAssignments();
}

 */

// an update for bulk student assignment

public interface SupervisorAssignmentService {
    StudentSupervisorDto assignStudentToSupervisor(Long studentId, Long supervisorId);

    List<StudentSupervisorDto> assignStudentsToSupervisor(List<Long> studentIds, Long supervisorId);

    List<StudentSupervisorDto> getStudentsBySupervisor(Long supervisorId);

    List<StudentSupervisorDto> getSupervisorsByStudent(Long studentId);

    List<StudentSupervisorDto> getAllAssignments();
}


