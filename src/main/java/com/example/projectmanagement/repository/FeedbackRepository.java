
package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}


