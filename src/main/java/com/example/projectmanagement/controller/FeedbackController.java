
/*

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

 */


//today

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public FeedbackDto createFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }

    @PutMapping("/{id}")
    public FeedbackDto updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackDto feedbackDto) {
        return feedbackService.updateFeedback(id, feedbackDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public FeedbackDto getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    @GetMapping("/review/{reviewId}")
    public List<FeedbackDto> getFeedbacksByReview(@PathVariable Long reviewId) {
        return feedbackService.getFeedbacksByReview(reviewId);
    }

    @GetMapping("/student/{studentId}")
    public List<FeedbackDto> getFeedbacksByStudent(@PathVariable Long studentId) {
        return feedbackService.getFeedbacksByStudent(studentId);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public List<FeedbackDto> getFeedbacksBySupervisor(@PathVariable Long supervisorId) {
        return feedbackService.getFeedbacksBySupervisor(supervisorId);
    }

}
