package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.ProgramDto;
import com.example.projectmanagement.model.Program;

import java.util.stream.Collectors;

public class ProgramMapper {

    public static ProgramDto toDTO(Program program) {
        if (program == null) return null;

        ProgramDto dto = new ProgramDto();
        dto.setProgramId(program.getId());
        dto.setName(program.getName());
        dto.setDescription(program.getDescription());

        if (program.getStudents() != null) {
            dto.setStudentIds(
                    program.getStudents()
                            .stream()
                            .map(student -> student.getId())
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public static Program toEntity(ProgramDto dto) {
        if (dto == null) return null;

        Program program = new Program();
        program.setId(dto.getProgramId());
        program.setName(dto.getName());
        program.setDescription(dto.getDescription());

        // Usually, setting students in entity from DTO is handled elsewhere (service)
        // because you only have student IDs here, not full User entities.

        return program;
    }
}
