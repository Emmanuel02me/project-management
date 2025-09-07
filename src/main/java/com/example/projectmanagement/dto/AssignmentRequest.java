package com.example.projectmanagement.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentRequest {
    private Long supervisorId;
    private List<Long> studentIds;
}
