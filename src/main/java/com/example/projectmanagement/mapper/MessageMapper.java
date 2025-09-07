package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.MessageDto;
import com.example.projectmanagement.model.Message;

public class MessageMapper {

    public static MessageDto toDTO(Message message) {
        if (message == null) return null;

        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setText(message.getText());
        dto.setRating(message.getRating());

        if (message.getSender() != null) {
            dto.setSenderId(message.getSender().getId());
            dto.setSenderName(message.getSender().getName());

            // ✅ derive role from sender user, not from message
            if (message.getSender().getRole() != null) {
                dto.setSenderRole(message.getSender().getRole().name());
            }
        }

        if (message.getReceiver() != null) {
            dto.setReceiverId(message.getReceiver().getId());
            dto.setReceiverName(message.getReceiver().getName());
        }

        return dto;
    }

    public static Message toEntity(MessageDto dto) {
        if (dto == null) return null;

        Message msg = new Message();
        msg.setText(dto.getText());
        msg.setRating(dto.getRating());
        // ❌ no need to set senderRole here, service will attach sender/receiver Users
        return msg;
    }
}
