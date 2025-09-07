package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.MessageDto;
import com.example.projectmanagement.mapper.MessageMapper;
import com.example.projectmanagement.model.Message;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.MessageRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageDto sendMessage(MessageDto dto) {
        User sender = userRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setText(dto.getText());
        message.setCreatedAt(LocalDateTime.now());
        message.setRating(dto.getRating());

        Message saved = messageRepository.save(message);

        // Build enriched DTO
        MessageDto response = new MessageDto();
        response.setId(saved.getId());
        response.setSenderId(sender.getId());
        response.setReceiverId(receiver.getId());
        response.setText(saved.getText());
        response.setRating(saved.getRating());
        response.setSenderName(sender.getName());
        response.setReceiverName(receiver.getName());
        response.setSenderRole(sender.getRole().name()); // assuming enum or string
        return response;
    }


    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getConversation(Long userId1, Long userId2) {
        return messageRepository
                .findBySender_IdAndReceiver_IdOrReceiver_IdAndSender_IdOrderByCreatedAtAsc(userId1, userId2, userId1, userId2)
                .stream()
                .map(MessageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getAllMessagesForUser(Long userId) {
        return messageRepository.findBySender_IdOrReceiver_IdOrderByCreatedAtAsc(userId, userId)
                .stream()
                .map(MessageMapper::toDTO)
                .collect(Collectors.toList());
    }
}
