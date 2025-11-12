package com.sagarmalasi.App.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sagarmalasi.App.entity.enums.ExamStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ExamStatus examStatus;



}
