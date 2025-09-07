
/*
package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {

    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Comments are required")
    @Size(min = 10, max = 1000, message = "Comments must be between 10 and 1000 characters")
    private String comments;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Reviewer ID is required")
    private Long reviewerId;
}

 */

// today

/*

//works fine
package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;

    // Server-side set on create/update if null
    private LocalDate date;

    @NotNull(message = "Comments are required")
    @Size(min = 10, max = 1000, message = "Comments must be between 10 and 1000 characters")
    private String comments;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Reviewer ID is required")
    private Long supervisor_id;

    // Extra (response convenience)
    private String projectTitle;
    private String supervisorName;
}


 */



/*

// works fine

package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;

    // Server-side set on create/update
    private LocalDate date;

    @NotNull(message = "Comments are required")
    @Size(min = 10, max = 1000, message = "Comments must be between 10 and 1000 characters")
    private String comments;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Supervisor ID is required")
    private Long supervisorId;

    // Convenience fields for response
    private String projectTitle;
    private String studentName;
    private String supervisorName;
}


 */


// tar 31

package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;

    private LocalDate date;

    @NotNull(message = "Comments are required")
    @Size(min = 10, max = 1000, message = "Comments must be between 10 and 1000 characters")
    private String comments;

    @NotNull(message = "Rating is required")
    private Integer rating;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Supervisor ID is required")
    private Long supervisorId;

    // New: helps frontend distinguish initiator (SUPERVISOR or STUDENT)
    private String initiatorRole;

    // Convenience fields
    private String projectTitle;
    private String studentName;
    private String supervisorName;
}



