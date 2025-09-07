/*
package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.mapper.FeedbackMapper;
import com.example.projectmanagement.model.Feedback;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.FeedbackRepository;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.FeedbackService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ReviewRepository reviewRepository,
                               UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto dto) {
        Feedback feedback = FeedbackMapper.toEntity(dto);

        if (dto.getReviewId() != null) {
            Review review = reviewRepository.findById(dto.getReviewId())
                    .orElseThrow(() -> new RuntimeException("Review not found"));
            feedback.setReview(review);
        }

        if (dto.getCoordinatorId() != null) {
            User coordinator = userRepository.findById(dto.getCoordinatorId())
                    .orElseThrow(() -> new RuntimeException("Coordinator not found"));
            feedback.setCoordinator(coordinator);
        }

        Feedback saved = feedbackRepository.save(feedback);
        return FeedbackMapper.toDTO(saved);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        return FeedbackMapper.toDTO(feedback);
    }
}

 */

//today



/*

//works fine

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.mapper.FeedbackMapper;
import com.example.projectmanagement.model.Feedback;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.FeedbackRepository;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ReviewRepository reviewRepository,
                               UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto dto) {
        Feedback feedback = FeedbackMapper.toEntity(dto);

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));

        if (student.getRole() != User.Role.STUDENT)
            throw new IllegalStateException("Only STUDENT can write feedback.");

        if (feedback.getDate() == null) feedback.setDate(LocalDate.now());
        feedback.setStudent(student);

        if (dto.getReviewId() != null) {
            // Only attach review if reviewId is provided
            Review review = reviewRepository.findById(dto.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Review not found: " + dto.getReviewId()));

            // Ensure student can only give feedback on their own review
            if (!review.getStudent().getId().equals(student.getId()))
                throw new IllegalStateException("Student can only feedback on reviews of their own project.");

            feedback.setReview(review);
        }

        Feedback saved = feedbackRepository.save(feedback);
        return FeedbackMapper.toDTO(saved);
    }


    @Override
    public FeedbackDto updateFeedback(Long id, FeedbackDto dto) {
        Feedback existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));

        existing.setFeedbackText(dto.getFeedbackText());
        existing.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        // Optionally allow moving to another review/student if valid
        if (dto.getReviewId() != null && !dto.getReviewId().equals(existing.getReview().getId())) {
            Review review = reviewRepository.findById(dto.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Review not found: " + dto.getReviewId()));
            existing.setReview(review);
        }
        if (dto.getStudentId() != null && !dto.getStudentId().equals(existing.getStudent().getId())) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));
            if (student.getRole() != User.Role.STUDENT)
                throw new IllegalStateException("User must be STUDENT.");
            existing.setStudent(student);
        }

        return FeedbackMapper.toDTO(existing);
    }

    @Override
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new IllegalArgumentException("Feedback not found: " + id);
        }
        feedbackRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackDto getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(FeedbackMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksByReview(Long reviewId) {
        return feedbackRepository.findByReviewId(reviewId)
                .stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksByStudent(Long studentId) {
        return feedbackRepository.findByStudentId(studentId)
                .stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksBySupervisor(Long supervisorId) {
        return feedbackRepository.findByReview_Supervisor_Id(supervisorId)
                .stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

}


 */

//tar 31

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.mapper.FeedbackMapper;
import com.example.projectmanagement.model.Feedback;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.FeedbackRepository;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               ReviewRepository reviewRepository,
                               UserRepository userRepository) {
        this.feedbackRepository = feedbackRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FeedbackDto createFeedback(FeedbackDto dto) {
        Feedback feedback = FeedbackMapper.toEntity(dto);

        if (dto.getStudentId() != null) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));
            feedback.setStudent(student);
        }

        if (dto.getSupervisorId() != null) {
            User supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));
            feedback.setSupervisor(supervisor);
        }

        if (dto.getReviewId() != null) {
            Review review = reviewRepository.findById(dto.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Review not found: " + dto.getReviewId()));
            feedback.setReview(review);
        }

        if (dto.getInitiatorId() != null) {
            User initiator = userRepository.findById(dto.getInitiatorId())
                    .orElseThrow(() -> new IllegalArgumentException("Initiator not found: " + dto.getInitiatorId()));
            feedback.setInitiator(initiator);
        }

        if (feedback.getDate() == null) feedback.setDate(LocalDate.now());

        Feedback saved = feedbackRepository.save(feedback);
        return FeedbackMapper.toDTO(saved);
    }

    @Override
    public FeedbackDto updateFeedback(Long id, FeedbackDto dto) {
        Feedback existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));

        existing.setFeedbackText(dto.getFeedbackText());
        existing.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        if (dto.getStudentId() != null && (existing.getStudent() == null || !dto.getStudentId().equals(existing.getStudent().getId()))) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));
            existing.setStudent(student);
        }

        if (dto.getSupervisorId() != null && (existing.getSupervisor() == null || !dto.getSupervisorId().equals(existing.getSupervisor().getId()))) {
            User supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));
            existing.setSupervisor(supervisor);
        }

        if (dto.getReviewId() != null && (existing.getReview() == null || !dto.getReviewId().equals(existing.getReview().getId()))) {
            Review review = reviewRepository.findById(dto.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("Review not found: " + dto.getReviewId()));
            existing.setReview(review);
        }

        if (dto.getInitiatorId() != null && (existing.getInitiator() == null || !dto.getInitiatorId().equals(existing.getInitiator().getId()))) {
            User initiator = userRepository.findById(dto.getInitiatorId())
                    .orElseThrow(() -> new IllegalArgumentException("Initiator not found: " + dto.getInitiatorId()));
            existing.setInitiator(initiator);
        }

        Feedback updated = feedbackRepository.save(existing);
        return FeedbackMapper.toDTO(updated);
    }

    @Override
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new IllegalArgumentException("Feedback not found: " + id);
        }
        feedbackRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FeedbackDto getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .map(FeedbackMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksByReview(Long reviewId) {
        return feedbackRepository.findByReviewId(reviewId).stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksByStudent(Long studentId) {
        return feedbackRepository.findByStudentId(studentId).stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackDto> getFeedbacksBySupervisor(Long supervisorId) {
        return feedbackRepository.findByReview_Supervisor_Id(supervisorId).stream()
                .map(FeedbackMapper::toDTO)
                .collect(Collectors.toList());
    }
}

