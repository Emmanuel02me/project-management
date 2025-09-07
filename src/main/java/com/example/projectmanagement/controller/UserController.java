/*

// this works

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add @Valid to trigger validation of the UserDto including password
    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        // You might want to hash password inside your service layer before saving to DB!
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //update user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDetails){
        return userService.updateUser(id, userDetails);
    }

    //delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User Deleted Successfully!!";
    }
}

 */


// an update tar 28

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.mapper.UserMapper;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =================== Create User ===================
    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        // Role-based validation happens inside the service
        return userService.createUserWithValidation(userDto);
    }

    // =================== Get All Users ===================
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // =================== Get User By ID ===================
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // =================== Update User By ID ===================
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // =================== Delete User By ID ===================
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User Deleted Successfully!";
    }

    // =================== Update Current Authenticated User ===================
    @PutMapping("/me")
    public UserDto updateCurrentUser(@RequestBody UserDto userDetails) {
        User loggedInUser = userService.getAuthenticatedUser();
        User updatedUser = userService.updateCurrentUser(loggedInUser, userDetails);
        return UserMapper.toDTO(updatedUser);
    }
}


