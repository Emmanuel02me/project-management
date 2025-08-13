package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
}
