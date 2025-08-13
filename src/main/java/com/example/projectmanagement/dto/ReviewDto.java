

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


