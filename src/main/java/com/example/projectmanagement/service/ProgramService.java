package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProgramDto;

import java.util.List;

public interface ProgramService {
    ProgramDto createProgram(ProgramDto programDto);
    List<ProgramDto> getAllPrograms();
    ProgramDto getProgramById(Long id);
}
