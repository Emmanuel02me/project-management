/*
package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}


 */

//today

package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByReviewId(Long reviewId);
    List<Feedback> findByStudentId(Long studentId);

    // NEW: find all feedbacks where the review belongs to this supervisor
    List<Feedback> findByReview_Supervisor_Id(Long supervisorId);
}
