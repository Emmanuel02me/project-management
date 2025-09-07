/*

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

 */

//today


/*
// works fine

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
            dto.setProjectTitle(review.getProject().getTitle());
        }
        if (review.getSupervisor() != null) {
            dto.setSupervisor_id(review.getSupervisor().getId());
            dto.setSupervisorName(review.getSupervisor().getName());
        }
        return dto;
    }

    public static Review toEntity(ReviewDto dto) {
        if (dto == null) return null;

        Review review = new Review();
        review.setId(dto.getId());
        // date will be set in service if null
        review.setDate(dto.getDate());
        review.setComments(dto.getComments());
        review.setRating(dto.getRating());
        // project & reviewer set in service by IDs
        return review;
    }
}

 */


/*


//works fine
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

        if (review.getStudent() != null) {
            dto.setStudentId(review.getStudent().getId());
            dto.setStudentName(review.getStudent().getName());
            dto.setProjectTitle(review.getStudent().getProjectTitle());
        }

        if (review.getSupervisor() != null) {
            dto.setSupervisorId(review.getSupervisor().getId());
            dto.setSupervisorName(review.getSupervisor().getName());
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
        // student & supervisor will be set in service
        return review;
    }
}

 */


//tar 31

package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.User;

public class ReviewMapper {

    public static ReviewDto toDTO(Review review) {
        if (review == null) return null;

        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId());
        dto.setDate(review.getDate());
        dto.setComments(review.getComments());
        dto.setRating(review.getRating());

        if (review.getStudent() != null) {
            dto.setStudentId(review.getStudent().getId());
            dto.setStudentName(review.getStudent().getName());
            dto.setProjectTitle(review.getStudent().getProjectTitle());
        }

        if (review.getSupervisor() != null) {
            dto.setSupervisorId(review.getSupervisor().getId());
            dto.setSupervisorName(review.getSupervisor().getName());
        }

        // Initiator role: if supervisor exists, mark as SUPERVISOR, else STUDENT
        if (review.getSupervisor() != null) {
            dto.setInitiatorRole(User.Role.SUPERVISOR.name());
        } else {
            dto.setInitiatorRole(User.Role.STUDENT.name());
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
        return review;
    }
}
