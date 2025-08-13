package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public FeedbackDto createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }

    @GetMapping
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public FeedbackDto getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }
}
