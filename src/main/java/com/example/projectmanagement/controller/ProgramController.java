/*
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProgramDto;
import com.example.projectmanagement.service.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @PostMapping
    public ProgramDto createProgram(@RequestBody ProgramDto programDto) {
        return programService.createProgram(programDto);
    }

    @GetMapping
    public List<ProgramDto> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/{id}")
    public ProgramDto getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id);
    }
}

 */

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProgramDto;
import com.example.projectmanagement.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    // Validate the request body with @Valid
    @PostMapping
    public ProgramDto createProgram(@Valid @RequestBody ProgramDto programDto) {
        return programService.createProgram(programDto);
    }

    @GetMapping
    public List<ProgramDto> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/{id}")
    public ProgramDto getProgramById(@PathVariable Long id) {
        return programService.getProgramById(id);
    }
}
