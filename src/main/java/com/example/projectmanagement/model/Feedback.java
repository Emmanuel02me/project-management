
/*

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedbackText;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private User coordinator;
}


 */

// today


/*

// works fine
package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedbackText;

    private LocalDate date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student; // ✅ was coordinator
}

 */

//tar 31


package com.example.projectmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedbackText;

    private LocalDate date;

    @ManyToOne(optional = true)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    // NEW: supervisor reference
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    // NEW: initiator (who sent this feedback)
    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
}
