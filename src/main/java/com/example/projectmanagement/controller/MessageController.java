package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.MessageDto;
import com.example.projectmanagement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public MessageDto sendMessage(@RequestBody MessageDto dto) {
        return messageService.sendMessage(dto);
    }

    @GetMapping("/conversation")
    public List<MessageDto> getConversation(@RequestParam Long user1, @RequestParam Long user2) {
        return messageService.getConversation(user1, user2);
    }

    @GetMapping("/all/{userId}")
    public List<MessageDto> getAllMessagesForUser(@PathVariable Long userId) {
        return messageService.getAllMessagesForUser(userId);
    }
}
