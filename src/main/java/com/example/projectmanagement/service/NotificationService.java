package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.NotificationDto;
import com.example.projectmanagement.model.Notification;
import com.example.projectmanagement.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public List<NotificationDto> getUnreadNotifications(Long recipientId, String recipientType) {
        return repository.findByRecipientIdAndRecipientTypeAndReadFalse(recipientId, recipientType)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NotificationDto sendNotification(Long recipientId, String recipientType, String message) {
        Notification notification = Notification.builder()
                .recipientId(recipientId)
                .recipientType(recipientType)
                .message(message)
                .read(false)
                .build();
        return toDto(repository.save(notification));
    }

    public void markAsRead(Long notificationId) {
        repository.findById(notificationId).ifPresent(n -> {
            n.setRead(true);
            repository.save(n);
        });
    }

    private NotificationDto toDto(Notification n) {
        return NotificationDto.builder()
                .id(n.getId())
                .recipientId(n.getRecipientId())
                .recipientType(n.getRecipientType())
                .message(n.getMessage())
                .read(n.isRead())
                .createdAt(n.getCreatedAt())
                .build();
    }
}
