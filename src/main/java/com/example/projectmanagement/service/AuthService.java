/*

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;

    // Register student (creates user + project)
    public User registerStudent(StudentRegistrationDTO dto) {
        User student = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .program(dto.getProgram())
                .role(User.Role.STUDENT)
                .build();

        userRepository.save(student);

        Project project = Project.builder()
                .title(dto.getProjectTitle())
                .student(student)
                .build();

        projectRepository.save(project);

        return student;
    }

    // Register supervisor or coordinator
    public User registerStaff(StaffRegistrationDTO dto) {
        User staff = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        return userRepository.save(staff);
    }
}


 */


/*

//today(this works fine)

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // Existing registerStudent() and registerStaff() methods...

    // ✅ New login method
    public User login(LoginRequest request) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Fetch user from DB
            return userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}

 */


/*
//final update




//works fine

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    // 🔑 Login with authentication + bind session
    public User login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Set security context → ensures session is created
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return the User entity (from DB)
        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Existing student registration
    public User registerStudent(StudentRegistrationDTO dto) {
        User student = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .program(dto.getProgram())
                .role(User.Role.STUDENT)
                .build();

        userRepository.save(student);

        Project project = Project.builder()
                .title(dto.getProjectTitle())
                .student(student)
                .build();

        projectRepository.save(project);

        return student;
    }

    // Existing staff registration
    public User registerStaff(StaffRegistrationDTO dto) {
        User staff = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        return userRepository.save(staff);
    }
}

 */

// an update

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    // 🔑 Login with authentication + bind session
    public User login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ Register student with program validation
    public User registerStudent(StudentRegistrationDTO dto) {
        List<String> allowedPrograms = List.of(
                "Computer Science",
                "Information and Communication Technology",
                "Data Science",
                "Computer Engineering",
                "Civil Engineering"
        );

        // Validate program
        if (!allowedPrograms.contains(dto.getProgram())) {
            throw new IllegalArgumentException(
                    "Invalid program name. Allowed programs: " + allowedPrograms
            );
        }

        // Check email uniqueness
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Create student
        User student = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .program(dto.getProgram())
                .role(User.Role.STUDENT)
                .build();

        userRepository.save(student);

        // Create project
        Project project = Project.builder()
                .title(dto.getProjectTitle())
                .student(student)
                .build();

        projectRepository.save(project);

        return student;
    }

    // Register staff (unchanged)
    public User registerStaff(StaffRegistrationDTO dto) {
        User staff = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        return userRepository.save(staff);
    }
}



