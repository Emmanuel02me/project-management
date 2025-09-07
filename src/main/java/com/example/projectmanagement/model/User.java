

/*
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

    private String program;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        STUDENT,
        SUPERVISOR,
        COORDINATOR
    }
}

 */
 /* @ManyToOne
    @JoinColumn(name = "program_id")
    @JsonIgnore  // this hides the Program from the JSON response
    private Program program;

  */

// today





 //this works fine



package com.example.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "users")  // avoid PostgreSQL reserved keyword
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    private String program; // Only for STUDENT

    @Column(name = "project_title")
    private String projectTitle;

    @Enumerated(EnumType.STRING)
    private Role role;
/*
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Project project;

    @OneToMany(mappedBy = "supervisor")
    private List<Project> supervisedProjects;


 */
    public enum Role {
        STUDENT,
        SUPERVISOR,
        COORDINATOR
    }
}









