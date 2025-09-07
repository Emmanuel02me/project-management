package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.MessageDto;

import java.util.List;

public interface MessageService {
    MessageDto sendMessage(MessageDto dto);
    List<MessageDto> getConversation(Long userId1, Long userId2);
    List<MessageDto> getAllMessagesForUser(Long userId); // for coordinator/student/supervisor
}
