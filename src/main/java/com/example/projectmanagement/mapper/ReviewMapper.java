package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.model.Review;

public class ReviewMapper {

    public static ReviewDto toDTO(Review review) {
        if (review == null) return null;

        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setDate(review.getDate());
        dto.setComments(review.getComments());
        dto.setRating(review.getRating());

        if (review.getProject() != null) {
            dto.setProjectId(review.getProject().getId());
        }
        if (review.getReviewer() != null) {
            dto.setReviewerId(review.getReviewer().getId());
        }

        return dto;
    }

    public static Review toEntity(ReviewDto dto) {
        if (dto == null) return null;

        Review review = new Review();
        review.setId(dto.getId());
        review.setDate(dto.getDate());
        review.setComments(dto.getComments());
        review.setRating(dto.getRating());

        // Project and Reviewer entities to be set in service from IDs

        return review;
    }
}
