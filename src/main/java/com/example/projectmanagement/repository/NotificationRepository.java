package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientIdAndRecipientTypeAndReadFalse(Long recipientId, String recipientType);
}
