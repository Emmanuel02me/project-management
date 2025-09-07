package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDto {
    private Long id;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotBlank
    private String text;

    private Integer rating;

    private String senderRole; // STUDENT, SUPERVISOR, COORDINATOR

    private String senderName;
    private String receiverName;
}
