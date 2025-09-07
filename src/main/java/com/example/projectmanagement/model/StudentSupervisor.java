
// new today tar 28
package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_supervisor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSupervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to student (user with role STUDENT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    // Link to supervisor (user with role SUPERVISOR)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id", nullable = false)
    private User supervisor;
}
