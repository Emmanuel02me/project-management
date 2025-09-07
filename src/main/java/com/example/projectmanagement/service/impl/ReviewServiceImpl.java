/*

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

 */

//today




/*

//works fine
package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.mapper.ReviewMapper;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.StudentSupervisor;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.StudentSupervisorRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StudentSupervisorRepository studentSupervisorRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             StudentSupervisorRepository studentSupervisorRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.studentSupervisorRepository = studentSupervisorRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto dto) {
        Review review = ReviewMapper.toEntity(dto);

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));

        User supervisor = userRepository.findById(dto.getSupervisorId())
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));

        if (supervisor.getRole() != User.Role.SUPERVISOR)
            throw new IllegalStateException("Only SUPERVISOR can create a review.");

        // Ensure supervisor is assigned to this student
        boolean assigned = studentSupervisorRepository.findByStudentId(student.getId())
                .stream()
                .anyMatch(ss -> ss.getSupervisor().getId().equals(supervisor.getId()));
        if (!assigned)
            throw new IllegalStateException("Supervisor not assigned to this student.");

        if (review.getDate() == null)
            review.setDate(LocalDate.now());

        review.setStudent(student);
        review.setSupervisor(supervisor);

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toDTO(saved);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto dto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + id));

        existing.setComments(dto.getComments());
        existing.setRating(dto.getRating());
        existing.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        if (dto.getStudentId() != null && !dto.getStudentId().equals(existing.getStudent().getId())) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));
            existing.setStudent(student);
        }

        if (dto.getSupervisorId() != null && !dto.getSupervisorId().equals(existing.getSupervisor().getId())) {
            User supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));
            existing.setSupervisor(supervisor);
        }

        return ReviewMapper.toDTO(existing);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id))
            throw new IllegalArgumentException("Review not found: " + id);
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByProject(Long projectId) {
        // Optional: If you no longer track Project entity, return all reviews where student.projectId == projectId
        // Here we skip actual project filtering since you use student.projectTitle
        return reviewRepository.findAll().stream()
                .filter(r -> r.getStudent() != null && r.getStudent().getId().equals(projectId)) // replace with actual logic
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsBySupervisor(Long supervisorId) {
        return reviewRepository.findBySupervisor_Id(supervisorId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByStudent(Long studentId) {
        return reviewRepository.findByStudent_Id(studentId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoordinatorReviewDto> getAllForCoordinator() {
        // Fetch all students
        List<User> students = userRepository.findAll().stream()
                .filter(u -> u.getRole() == User.Role.STUDENT)
                .collect(Collectors.toList());

        return students.stream().map(student -> {
            // All reviews for this student
            List<Review> reviews = reviewRepository.findByStudent_Id(student.getId());

            // Latest feedback by date (null-safe)
            String latestFeedback = reviews.stream()
                    .max(Comparator.comparing(Review::getDate))
                    .map(Review::getComments)
                    .orElse("N/A");

            // Assigned supervisor (if any)
            StudentSupervisor assignment = studentSupervisorRepository.findByStudentId(student.getId())
                    .stream()
                    .findFirst()
                    .orElse(null);

            Long supervisorId = assignment != null ? assignment.getSupervisor().getId() : null;
            String supervisorName = assignment != null ? assignment.getSupervisor().getName() : "N/A";

            // Count all reviews (instead of only rating 8-10)
            long reviewCount = reviews.size();

            return CoordinatorReviewDto.builder()
                    .studentId(student.getId())
                    .studentName(student.getName())
                    .studentEmail(student.getEmail())
                    .projectTitle(student.getProjectTitle())
                    .supervisorId(supervisorId)
                    .supervisorName(supervisorName)
                    .reviewCount(reviewCount) // <-- now correct
                    .latestFeedback(latestFeedback)
                    .build();
        }).collect(Collectors.toList());
    }


}


 */

//today

package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.mapper.ReviewMapper;
import com.example.projectmanagement.model.Review;
import com.example.projectmanagement.model.StudentSupervisor;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repository.ReviewRepository;
import com.example.projectmanagement.repository.StudentSupervisorRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.service.ReviewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StudentSupervisorRepository studentSupervisorRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             StudentSupervisorRepository studentSupervisorRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.studentSupervisorRepository = studentSupervisorRepository;
    }

    @Override
    public ReviewDto createReview(ReviewDto dto) {
        Review review = ReviewMapper.toEntity(dto);

        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));

        User supervisor = userRepository.findById(dto.getSupervisorId())
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));

        if (review.getDate() == null) {
            review.setDate(LocalDate.now());
        }

        review.setStudent(student);
        review.setSupervisor(supervisor);

        Review saved = reviewRepository.save(review);
        return ReviewMapper.toDTO(saved);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto dto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + id));

        existing.setComments(dto.getComments());
        existing.setRating(dto.getRating());
        existing.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());

        if (dto.getStudentId() != null && !dto.getStudentId().equals(existing.getStudent().getId())) {
            User student = userRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new IllegalArgumentException("Student not found: " + dto.getStudentId()));
            existing.setStudent(student);
        }

        if (dto.getSupervisorId() != null && !dto.getSupervisorId().equals(existing.getSupervisor().getId())) {
            User supervisor = userRepository.findById(dto.getSupervisorId())
                    .orElseThrow(() -> new IllegalArgumentException("Supervisor not found: " + dto.getSupervisorId()));
            existing.setSupervisor(supervisor);
        }

        Review saved = reviewRepository.save(existing);
        return ReviewMapper.toDTO(saved);
    }

    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id))
            throw new IllegalArgumentException("Review not found: " + id);
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Review not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByProject(Long projectId) {
        // Optional: If you no longer track Project entity, return all reviews where student.projectId == projectId
        // Here we skip actual project filtering since you use student.projectTitle
        return reviewRepository.findAll().stream()
                .filter(r -> r.getStudent() != null && r.getStudent().getId().equals(projectId)) // replace with actual logic
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsBySupervisor(Long supervisorId) {
        return reviewRepository.findBySupervisor_Id(supervisorId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getReviewsByStudent(Long studentId) {
        return reviewRepository.findByStudent_Id(studentId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoordinatorReviewDto> getAllForCoordinator() {
        List<User> students = userRepository.findAll().stream()
                .filter(u -> u.getRole() == User.Role.STUDENT)
                .collect(Collectors.toList());

        return students.stream().map(student -> {
            List<Review> reviews = reviewRepository.findByStudent_Id(student.getId());

            String latestFeedback = reviews.stream()
                    .max(Comparator.comparing(Review::getDate))
                    .map(Review::getComments)
                    .orElse("N/A");

            StudentSupervisor assignment = studentSupervisorRepository.findByStudentId(student.getId())
                    .stream()
                    .findFirst()
                    .orElse(null);

            Long supervisorId = assignment != null ? assignment.getSupervisor().getId() : null;
            String supervisorName = assignment != null ? assignment.getSupervisor().getName() : "N/A";

            long reviewCount = reviews.size();

            return CoordinatorReviewDto.builder()
                    .studentId(student.getId())
                    .studentName(student.getName())
                    .studentEmail(student.getEmail())
                    .projectTitle(student.getProjectTitle())
                    .supervisorId(supervisorId)
                    .supervisorName(supervisorName)
                    .reviewCount(reviewCount)
                    .latestFeedback(latestFeedback)
                    .build();
        }).collect(Collectors.toList());
    }
}







