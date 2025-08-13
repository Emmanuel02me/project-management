

package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {}


