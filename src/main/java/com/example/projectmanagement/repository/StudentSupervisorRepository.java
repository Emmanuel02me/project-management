// this is new tar 28

package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.StudentSupervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSupervisorRepository extends JpaRepository<StudentSupervisor, Long> {
    List<StudentSupervisor> findBySupervisorId(Long supervisorId);
    List<StudentSupervisor> findByStudentId(Long studentId);
}
