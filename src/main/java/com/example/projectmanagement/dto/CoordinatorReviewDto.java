package com.example.projectmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   // ✅ This adds the builder
public class CoordinatorReviewDto {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String projectTitle;
    private Long supervisorId;     // ✅ you are also using supervisorId in service
    private String supervisorName;
    private Long reviewCount;
    private String latestFeedback;
}
