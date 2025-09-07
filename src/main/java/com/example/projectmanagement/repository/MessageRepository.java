package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Messages between two users, chronological
    List<Message> findBySender_IdAndReceiver_IdOrReceiver_IdAndSender_IdOrderByCreatedAtAsc(
            Long senderId1, Long receiverId1, Long senderId2, Long receiverId2);

    // Messages by user (any)
    List<Message> findBySender_IdOrReceiver_IdOrderByCreatedAtAsc(Long userId1, Long userId2);
}
