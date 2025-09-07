package com.example.projectmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private Long recipientId; // studentId or supervisorId
    private String recipientType; // "STUDENT" or "SUPERVISOR"
    private String message;
    private boolean read;
    private LocalDateTime createdAt;
}
