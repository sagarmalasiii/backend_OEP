package com.sagarmalasi.App.entity;

import com.sagarmalasi.App.entity.enums.ExamStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "start_registration_time",nullable = false)
    private LocalDateTime startRegistrationTime;
    @Column(name = "end_registration_time",nullable = false)
    private LocalDateTime endRegistrationTime;
    @Column(name = "exam_start_time",nullable = false)
    private LocalDateTime examStartTime;
    @Column(name = "exam_end_time",nullable = false)
    private LocalDateTime examEndTime;

    @Transient
    public long getDurationInMinutes() {
        if (examStartTime == null || examEndTime == null) return 0;
        return Duration.between(examStartTime, examEndTime).toMinutes();
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private ExamStatus examStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id",nullable = false)
    private Institute institute;

    @OneToMany(mappedBy = "exam",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ExamRegistration> registrationList = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
