/*

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.FeedbackDto;
import com.example.projectmanagement.model.Feedback;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto dto);
    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto getFeedbackById(Long id);
}


 */

//today

package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto dto);
    FeedbackDto updateFeedback(Long id, FeedbackDto dto);
    void deleteFeedback(Long id);

    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto getFeedbackById(Long id);
    
    List<FeedbackDto> getFeedbacksByReview(Long reviewId);
    List<FeedbackDto> getFeedbacksByStudent(Long studentId);
    List<FeedbackDto> getFeedbacksBySupervisor(Long supervisorId);

}
