

package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Collection<Object> findAllBySupervisorId(Long id);
}


