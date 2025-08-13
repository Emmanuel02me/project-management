package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
