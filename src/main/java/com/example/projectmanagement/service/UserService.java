
/*

//this works

package com.example.projectmanagement.service;

 // assuming your DTO is here
import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.model.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDTO);  // Accept and return DTO
    List<UserDto> getAllUsers();           // Return list of DTOs
        UserDto getUserById(Long id);          // Return a single DTO

    // user update today
    User updateUser(Long id, UserDto userDetails);
    void deleteUser(Long id);

}

 */

// tar 28

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.model.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDTO);          // Old method
    UserDto createUserWithValidation(UserDto userDTO); // New role-based validation
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    User updateUser(Long id, UserDto userDetails);
    void deleteUser(Long id);

    // /users/me endpoints
    User getAuthenticatedUser();
    User updateCurrentUser(User user, UserDto userDetails);
}


