package com.example.projectmanagement.dto;

import lombok.Data;
import com.example.projectmanagement.model.User;

@Data
public class StaffRegistrationDTO {
    private String name;
    private String email;
    private String password;
    private User.Role role; // SUPERVISOR or COORDINATOR
}
