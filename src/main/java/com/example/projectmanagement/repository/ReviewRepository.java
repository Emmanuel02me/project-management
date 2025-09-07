
/*
package com.example.projectmanagement.repository;

import com.example.projectmanagement.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {}


 */

//today


/*

// works fine
package com.example.projectmanagement.repository;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
    SELECT new com.example.projectmanagement.dto.CoordinatorReviewDto(
        s.id,
        s.name,
        s.email,
        s.projectTitle,
        sup.id,
        sup.name,
        COUNT(r.id),
        null
    )
    FROM User s
    LEFT JOIN Review r ON r.student.id = s.id
    LEFT JOIN User sup ON r.supervisor.id = sup.id
    WHERE s.role = 'STUDENT'
    GROUP BY s.id, s.name, s.email, s.projectTitle, sup.id, sup.name
""")
    List<CoordinatorReviewDto> findAllForCoordinator();


    List<Review> findByStudent_Id(Long studentId);
    List<Review> findBySupervisor_Id(Long supervisorId);

}

 */

// tar 31

package com.example.projectmanagement.repository;

import com.example.projectmanagement.dto.CoordinatorReviewDto;
import com.example.projectmanagement.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
    SELECT new com.example.projectmanagement.dto.CoordinatorReviewDto(
        s.id,
        s.name,
        s.email,
        s.projectTitle,
        sup.id,
        sup.name,
        COUNT(r.id),
        null
    )
    FROM User s
    LEFT JOIN Review r ON r.student.id = s.id
    LEFT JOIN User sup ON r.supervisor.id = sup.id
    WHERE s.role = 'STUDENT'
    GROUP BY s.id, s.name, s.email, s.projectTitle, sup.id, sup.name
""")
    List<CoordinatorReviewDto> findAllForCoordinator();

    List<Review> findByStudent_Id(Long studentId);
    List<Review> findBySupervisor_Id(Long supervisorId);

}
