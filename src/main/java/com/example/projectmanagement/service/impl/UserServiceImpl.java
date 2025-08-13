package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.mapper.UserMapper;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
