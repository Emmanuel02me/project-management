/*

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
}

 */


//today


/*

// works fine
package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    void deleteReview(Long id);

    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getReviewsByProject(Long projectId);
    List<ReviewDto> getReviewsBySupervisor(Long supervisorId);
    List<ReviewDto> getReviewsByStudent(Long studentId);
    List<CoordinatorReviewDto> getAllForCoordinator();


}

 */

//tar 31

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    void deleteReview(Long id);

    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getReviewsByProject(Long projectId);
    List<ReviewDto> getReviewsBySupervisor(Long supervisorId);
    List<ReviewDto> getReviewsByStudent(Long studentId);
    List<CoordinatorReviewDto> getAllForCoordinator();
}
