/*

// works fine

package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public enum ProjectStatus {
        IN_PROGRESS,
        COMPLETED,
        PENDING
    }

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private User student;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

 */


//just an update

package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public enum ProjectStatus {
        IN_PROGRESS,
        COMPLETED,
        PENDING
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // One Student ↔ One Project
    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private User student;

    // Many Projects ↔ One Supervisor
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
