package com.sagarmalasi.App.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamRegistrationDTO {

    private Long id;
    private String registrationStatus;
    @NotNull
    private Long studentId;
    @NotNull
    private Long examId;

}
