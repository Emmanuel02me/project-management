
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


