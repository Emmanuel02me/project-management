
/*
// works fine

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.mapper.UserMapper;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProgramRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public UserServiceImpl(UserRepository userRepository, ProgramRepository programRepository) {
        this.userRepository = userRepository;
        this.programRepository = programRepository;
    }

    @Override
    public UserDto createUser(UserDto userDTO) {
        User user = UserMapper.toEntity(userDTO);  // Convert DTO to entity
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);        // Convert back to DTO
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)             // Map entity to DTO
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //today update and delete methods
    @Override
    public User updateUser(Long id, UserDto userDetails){
        // finding user first
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // update user
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setEmail(userDetails.getEmail());

        return userRepository.save(existingUser);


    }

    @Override
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteById(id);
    }
}

 */

// an update

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.mapper.UserMapper;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProgramRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.groups.Default;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository,
                           ProgramRepository programRepository,
                           PasswordEncoder passwordEncoder,
                           Validator validator) {
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @Override
    public UserDto createUser(UserDto userDTO) {
        // Simple save without role-based validation
        User user = UserMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public UserDto createUserWithValidation(UserDto userDTO) {
        // Determine validation groups based on role
        Class<?>[] groups;
        if ("STUDENT".equalsIgnoreCase(userDTO.getRole())) {
            groups = new Class[]{jakarta.validation.groups.Default.class, UserDto.StudentValidation.class};
        } else {
            groups = new Class[]{jakarta.validation.groups.Default.class};
        }

// Validate
        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDTO, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }


        // Encode password before saving
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        // Map DTO to entity and save
        User user = UserMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, UserDto userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (userDetails.getEmail() != null) existingUser.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    @Override
    public User updateCurrentUser(User user, UserDto userDetails) {
        if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        if (userDetails.getProgram() != null) user.setProgram(userDetails.getProgram());
        if (userDetails.getProjectTitle() != null) user.setProjectTitle(userDetails.getProjectTitle());
        return userRepository.save(user);
    }
}




