package com.sagarmalasi.App.entity;

import com.sagarmalasi.App.entity.enums.ExamStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "exams",
        uniqueConstraints = @UniqueConstraint(columnNames = {"institute_id", "title"})
)

public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(length = 500)
    private String details;

    @Column(nullable = false,unique = true,length = 50)
    private String examCode;

    private Integer totalQuestions;

    @NotNull
    private LocalDateTime startRegistrationTime;
    @NotNull
    private LocalDateTime endRegistrationTime;
    @NotNull
    private LocalDateTime examStartTime;
    @NotNull
    private LocalDateTime examEndTime;

    @Transient
    public long getDurationInMinutes() {
        if (examStartTime == null || examEndTime == null) return 0;
        return Duration.between(examStartTime, examEndTime).toMinutes();
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ExamStatus examStatus = ExamStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id",nullable = false)
    private Institute institute;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
