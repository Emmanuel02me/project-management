package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.mapper.ReviewMapper;
import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ProjectRepository;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ProjectRepository projectRepository,
                             UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto dto) {
        Review review = ReviewMapper.toEntity(dto);

        if (dto.getProjectId() != null) {
            Project project = projectRepository.findById(dto.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            review.setProject(project);
        }

        if (dto.getReviewerId() != null) {
            User reviewer = userRepository.findById(dto.getReviewerId())
                    .orElseThrow(() -> new RuntimeException("Reviewer not found"));
            review.setReviewer(reviewer);
        }

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toDTO(saved);
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return ReviewMapper.toDTO(review);
    }
}
