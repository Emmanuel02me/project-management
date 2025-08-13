package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ProgramDto;
import com.example.projectmanagement.mapper.ProgramMapper;
import com.example.projectmanagement.model.Program;
import com.example.projectmanagement.repository.ProgramRepository;
import com.example.projectmanagement.service.ProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        Program program = ProgramMapper.toEntity(programDto);
        // Save program entity, students handling separately if needed
        Program saved = programRepository.save(program);
        return ProgramMapper.toDTO(saved);
    }

    @Override
    public List<ProgramDto> getAllPrograms() {
        List<Program> programs = programRepository.findAll();
        return programs.stream()
                .map(ProgramMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProgramDto getProgramById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + id));
        return ProgramMapper.toDTO(program);
    }
}
