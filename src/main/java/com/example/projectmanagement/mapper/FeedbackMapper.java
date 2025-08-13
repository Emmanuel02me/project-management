package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.model.Feedback;

public class FeedbackMapper {

    public static FeedbackDto toDTO(Feedback feedback) {
        if (feedback == null) return null;

        FeedbackDto dto = new FeedbackDto();
        dto.setId(feedback.getId());
        dto.setFeedbackText(feedback.getFeedbackText());
        dto.setDate(feedback.getDate());

        if (feedback.getReview() != null) {
            dto.setReviewId(feedback.getReview().getId());
        }
        if (feedback.getCoordinator() != null) {
            dto.setCoordinatorId(feedback.getCoordinator().getId());
        }
        return dto;
    }

    public static Feedback toEntity(FeedbackDto dto) {
        if (dto == null) return null;

        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setFeedbackText(dto.getFeedbackText());
        feedback.setDate(dto.getDate());

        // Review and Coordinator entities to be set in service from IDs

        return feedback;
    }
}
