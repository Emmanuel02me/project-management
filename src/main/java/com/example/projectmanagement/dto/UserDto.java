package com.example.projectmanagement.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String role;
    private Long programId;
    private String programName;
}
