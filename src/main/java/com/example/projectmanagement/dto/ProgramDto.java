package com.example.projectmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class ProgramDto {

    private Long programId;  // Optional for new programs (DB-generated)

    @NotBlank(message = "Program name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    // Optional list, can be empty or null
    private List<@NotNull(message = "Student ID cannot be null") Long> studentIds;
}
