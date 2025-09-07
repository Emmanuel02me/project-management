/*

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

 */


//today


/*

// this work fine
package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<ReviewDto> getReviewsByProject(@PathVariable Long projectId) {
        return reviewService.getReviewsByProject(projectId);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public List<ReviewDto> getReviewsBySupervisor(@PathVariable Long supervisorId) {
        return reviewService.getReviewsBySupervisor(supervisorId);
    }

    // NEW endpoint for student
    @GetMapping("/student/{studentId}")
    public List<ReviewDto> getReviewsByStudent(@PathVariable Long studentId) {
        return reviewService.getReviewsByStudent(studentId);
    }

    @GetMapping("/coordinator/all-reviews")
    public List<CoordinatorReviewDto> getAllForCoordinator() {
        return reviewService.getAllForCoordinator();
    }

}


 */

// tar 31

package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;
import com.example.projectmanagement.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<ReviewDto> getReviewsByProject(@PathVariable Long projectId) {
        return reviewService.getReviewsByProject(projectId);
    }

    @GetMapping("/supervisor/{supervisorId}")
    public List<ReviewDto> getReviewsBySupervisor(@PathVariable Long supervisorId) {
        return reviewService.getReviewsBySupervisor(supervisorId);
    }

    @GetMapping("/student/{studentId}")
    public List<ReviewDto> getReviewsByStudent(@PathVariable Long studentId) {
        return reviewService.getReviewsByStudent(studentId);
    }

    @GetMapping("/coordinator/all-reviews")
    public List<CoordinatorReviewDto> getAllForCoordinator() {
        return reviewService.getAllForCoordinator();
    }

}
