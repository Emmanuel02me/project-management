package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.NotificationDto;
import com.example.projectmanagement.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    // Get unread notifications for student or supervisor
    @GetMapping("/{recipientType}/{recipientId}")
    public ResponseEntity<List<NotificationDto>> getUnread(
            @PathVariable String recipientType,
            @PathVariable Long recipientId) {
        return ResponseEntity.ok(service.getUnreadNotifications(recipientId, recipientType.toUpperCase()));
    }

    // Coordinator sends notification
    @PostMapping
    public ResponseEntity<NotificationDto> sendNotification(
            @RequestBody NotificationDto request) {
        return ResponseEntity.ok(
                service.sendNotification(
                        request.getRecipientId(),
                        request.getRecipientType().toUpperCase(),
                        request.getMessage()
                )
        );
    }

    // Mark as read
    @PostMapping("/read/{id}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        service.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
