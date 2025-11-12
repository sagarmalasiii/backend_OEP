package com.sagarmalasi.App.entity;

import com.sagarmalasi.App.entity.enums.RegistrationStatus;
import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ExamRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "registration_status")
    private RegistrationStatus registrationStatus;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "student_id",nullable = false)
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id",nullable = false)
    private Exam exam;

    @CreationTimestamp
    @Column(name = "registered_time",updatable = false)
    private LocalDateTime registeredAt;
}
