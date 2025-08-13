package com.example.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")  //this avoid PostgreSQL reserved keyword "User"
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        STUDENT,
        SUPERVISOR,
        COORDINATOR
    }


    @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonIgnore  // this hides the Program from the JSON response
    private Program program;


}
