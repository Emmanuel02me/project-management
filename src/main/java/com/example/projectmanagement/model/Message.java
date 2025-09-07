package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String text;

    // Optional: for reviews with rating
    private Integer rating;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Sender of the message
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Receiver of the message (student, supervisor, coordinator)
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // Role of sender (STUDENT / SUPERVISOR / COORDINATOR)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private User.Role senderRole;

    // Optional: thread or conversation grouping
    private Long conversationId; // can be used later for grouping messages

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
