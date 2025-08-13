
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




