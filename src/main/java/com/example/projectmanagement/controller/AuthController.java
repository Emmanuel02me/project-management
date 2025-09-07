
/*
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok(user); // Later you can return token
    }
}


 */

// today
/*
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/student")
    public ResponseEntity<User> registerStudent(@RequestBody StudentRegistrationDTO dto) {
        return ResponseEntity.ok(authService.registerStudent(dto));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody StaffRegistrationDTO dto) {
        return ResponseEntity.ok(authService.registerStaff(dto));
    }
}



 */

/*

// today(this works fine)

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request);

        // Return email and role
        return new LoginResponse(user.getEmail(), user.getRole().name());
    }
}

 */


//final update

/*


// works fine

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return new LoginResponse(user.getEmail(), user.getRole().name(), user.getName());
    }

    @GetMapping("/me")
    public LoginResponse me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not logged in");
        }

        Object principal = authentication.getPrincipal();
        String name;
        String email;
        String role;

        if (principal instanceof User) {
            User user = (User) principal;
            email = user.getEmail();
            role = user.getRole().name();
            name = user.getName();
        } else if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = userDetails.getUsername();
            role = authentication.getAuthorities().iterator().next().getAuthority();
            name = userDetails.getUsername();
        } else {
            throw new RuntimeException("Unexpected principal type");
        }

        return new LoginResponse(email, role, name);
    }

    // 🚀 /logout handled automatically by Spring Security (config in SecurityConfig)
}


 */


// an update
/*
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return new LoginResponse(user.getEmail(), user.getRole().name(), user.getName());
    }

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDTO dto) {
        try {
            User student = authService.registerStudent(dto);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody StaffRegistrationDTO dto) {
        return ResponseEntity.ok(authService.registerStaff(dto));
    }
}

 */
/*

// update keep it work

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return new LoginResponse(user.getEmail(), user.getRole().name(), user.getName());
    }

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDTO dto) {
        try {
            User student = authService.registerStudent(dto);
            return ResponseEntity.ok(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody StaffRegistrationDTO dto) {
        return ResponseEntity.ok(authService.registerStaff(dto));
    }

    @GetMapping("/me")
    public LoginResponse me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not logged in");
        }

        Object principal = authentication.getPrincipal();
        String name;
        String email;
        String role;

        if (principal instanceof User) {
            User user = (User) principal;
            email = user.getEmail();
            role = user.getRole().name();
            name = user.getName();
        } else if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            email = userDetails.getUsername();
            role = authentication.getAuthorities().iterator().next().getAuthority();
            name = userDetails.getUsername();
        } else {
            throw new RuntimeException("Unexpected principal type");
        }

        return new LoginResponse(email, role, name);
    }
}

 */

/*

// an update to enhance supervisor fetch
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.dto.StudentRegistrationDTO;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;




    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok(new LoginResponse(
                user.getEmail(),
                user.getRole().name(),
                user.getName()
        ));
    }

    // 🔹 Register Student
    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody StudentRegistrationDTO dto) {
        try {
            User student = authService.registerStudent(dto);
            return ResponseEntity.ok(new LoginResponse(
                    student.getEmail(),
                    student.getRole().name(),
                    student.getName()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Register Staff
    @PostMapping("/register/staff")
    public ResponseEntity<LoginResponse> registerStaff(@RequestBody StaffRegistrationDTO dto) {
        User staff = authService.registerStaff(dto);
        return ResponseEntity.ok(new LoginResponse(
                staff.getEmail(),
                staff.getRole().name(),
                staff.getName()
        ));
    }

    // 🔹 Get currently authenticated user (for frontend persistence)
    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        String name;
        String email;
        String role;

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            email = user.getEmail();
            role = user.getRole().name();
            name = user.getName();
        } else if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
            role = authentication.getAuthorities().iterator().next().getAuthority();
            name = userDetails.getUsername();
        } else {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(new LoginResponse(email, role, name));
    }
}

 */

// an udate to ensure numeric ids

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.dto.LoginRequest;
import com.example.projectmanagement.dto.LoginResponse;
import com.example.projectmanagement.dto.StaffRegistrationDTO;
import com.example.projectmanagement.dto.ForgotPasswordRequest;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.service.UserService;
import com.example.projectmanagement.service.AuthService;
import com.example.projectmanagement.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserRepository userRepository; // Add this injection

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok(new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name(),
                user.getName()
        ));
    }

    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid UserDto userDto) {
        try {
            // Force role
            userDto.setRole("STUDENT");

            UserDto studentDto = userService.createUserWithValidation(userDto);

            return ResponseEntity.ok(new LoginResponse(
                    studentDto.getId(),
                    studentDto.getEmail(),
                    studentDto.getRole(),
                    studentDto.getName()
            ));
        } catch (ConstraintViolationException e) {
            String errors = e.getConstraintViolations().stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .reduce("", (a, b) -> a + "; " + b);
            return ResponseEntity.badRequest().body(errors);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/staff")
    public ResponseEntity<LoginResponse> registerStaff(@RequestBody StaffRegistrationDTO dto) {
        User staff = authService.registerStaff(dto);
        return ResponseEntity.ok(new LoginResponse(
                staff.getId(),
                staff.getEmail(),
                staff.getRole().name(),
                staff.getName()
        ));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        if (authentication.getPrincipal() instanceof User user) {
            return ResponseEntity.ok(new LoginResponse(
                    user.getId(),
                    user.getEmail(),
                    user.getRole().name(),
                    user.getName()
            ));
        }

        return ResponseEntity.status(500).build();
    }

    // 🚀 NEW: Forgot Password Endpoint
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            // Find user by name and email
            User user = userRepository.findByNameAndEmail(request.getName(), request.getEmail())
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(404).body("No account found with this name and email combination");
            }

            // Reset password to user's name
            user.setPassword(user.getName());
            userRepository.save(user);

            // Return user data (similar to login response)
            return ResponseEntity.ok(new LoginResponse(
                    user.getId(),
                    user.getEmail(),
                    user.getRole().name(),
                    user.getName()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Password reset failed: " + e.getMessage());
        }
    }
}
