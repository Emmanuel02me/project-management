

package com.example.projectmanagement.service;

 // assuming your DTO is here
import com.example.projectmanagement.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDTO);  // Accept and return DTO
    List<UserDto> getAllUsers();           // Return list of DTOs
        UserDto getUserById(Long id);          // Return a single DTO
}

