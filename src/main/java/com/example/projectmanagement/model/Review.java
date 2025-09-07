
/*


//works fine
package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String comments;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;  // Supervisor
}

 */


/*

//works fine
package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String comments;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;  // Student (owner of the project)

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;  // Supervisor
}


 */


//tar 31

package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String comments;

    private Integer rating;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;
}
