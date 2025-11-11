package com.sagarmalasi.App.dto;

import com.sagarmalasi.App.entity.enums.ExamStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class ExamDTO {
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String examCode;
    private String details;
    private Integer totalQuestions;
    @NotNull
    private LocalDateTime startRegistrationTime;
    @NotNull
    private LocalDateTime endRegistrationTime;
    @NotNull
    private LocalDateTime examStartTime;
    @NotNull
    private LocalDateTime examEndTime;

    private Long durationInMinutes;
    private Long instituteId;
    private ExamStatus examStatus;




}
