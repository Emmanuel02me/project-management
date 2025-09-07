
/*
//works fine
package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    // hii inaonekana kwenye postman as a response baada ya login kuwa successfully

    private String email;
    private String role;
    private String name;
}

 */

//an update to include numeric ids
package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;      // 🔹 Add numeric ID
    private String email;
    private String role;
    private String name;
}

