/*
package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;

@Data
public class FeedbackDto {

    private Long id;

    @NotNull(message = "Feedback text is required")
    @Size(min = 5, max = 500, message = "Feedback text must be between 5 and 500 characters")
    private String feedbackText;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Review ID is required")
    private Long reviewId;

    @NotNull(message = "Coordinator ID is required")
    private Long coordinatorId;


}


 */

//today

/*

//works fine
package com.example.projectmanagement.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FeedbackDto {

    private Long id;

    @NotNull(message = "Feedback text is required")
    @Size(min = 5, max = 500)
    private String feedbackText;

    private LocalDate date;

    @NotNull(message = "Review ID is required")
    private Long reviewId;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    // Convenience
    private String studentName;
    private String reviewSummary;
}



 */

//tar 31

package com.example.projectmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FeedbackDto {

    private Long id;

    @NotNull(message = "Feedback text is required")
    @Size(min = 3, max = 500)
    private String feedbackText;

    private LocalDate date;

    @NotNull(message = "Review ID is required")
    private Long reviewId;

    private Long studentId;       // optional if supervisor initiates
    private Long supervisorId;    // optional if student initiates

    // Initiator info
    private Long initiatorId;
    private String initiatorName;
    private String initiatorRole;

    // Convenience
    private String studentName;
    private String reviewSummary;
}
