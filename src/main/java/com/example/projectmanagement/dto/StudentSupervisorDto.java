//today tar 28

package com.example.projectmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSupervisorDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private String projectTitle;
    private String studentProgram;

    private Long supervisorId;
    private String supervisorName;
    private String supervisorEmail;
}




