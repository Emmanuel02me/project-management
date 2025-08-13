package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }
}
