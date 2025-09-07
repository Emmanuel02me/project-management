package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentAssignmentDto {
    private Long studentId;
    private String studentName;
    private String supervisorName;
}
