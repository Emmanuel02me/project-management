/*

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

 */


//today



/*


//works fine
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
            String c = feedback.getReview().getComments();
            if (c != null) dto.setReviewSummary(c.substring(0, Math.min(50, c.length())));
        }

        if (feedback.getStudent() != null) {
            dto.setStudentId(feedback.getStudent().getId());
            dto.setStudentName(feedback.getStudent().getName());
        }

        return dto;
    }

    public static Feedback toEntity(FeedbackDto dto) {
        if (dto == null) return null;

        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setFeedbackText(dto.getFeedbackText());
        feedback.setDate(dto.getDate());
        // review & student set in service
        return feedback;
    }
}

 */


// tar 31


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
            String c = feedback.getReview().getComments();
            if (c != null) dto.setReviewSummary(c.substring(0, Math.min(50, c.length())));
        }

        if (feedback.getStudent() != null) {
            dto.setStudentId(feedback.getStudent().getId());
            dto.setStudentName(feedback.getStudent().getName());
        }

        if (feedback.getSupervisor() != null) {
            dto.setSupervisorId(feedback.getSupervisor().getId());
        }

        if (feedback.getInitiator() != null) {
            dto.setInitiatorId(feedback.getInitiator().getId());
            dto.setInitiatorName(feedback.getInitiator().getName());
            dto.setInitiatorRole(feedback.getInitiator().getRole().name());
        }

        return dto;
    }

    public static Feedback toEntity(FeedbackDto dto) {
        if (dto == null) return null;

        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setFeedbackText(dto.getFeedbackText());
        feedback.setDate(dto.getDate());
        // review, student, supervisor, initiator set in service
        return feedback;
    }
}
