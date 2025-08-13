

package com.example.projectmanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProgramDto {
    private Long programId;
    private String name;
    private String description;

    // For simplicity, list of student IDs or names
    private List<Long> studentIds;

    // or just count
    // private int studentCount;
}
