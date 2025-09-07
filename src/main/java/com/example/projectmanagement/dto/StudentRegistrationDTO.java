package com.example.projectmanagement.dto;

import lombok.Data;

@Data
public class StudentRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private String program;
    private String projectTitle;
}
