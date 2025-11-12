package com.sagarmalasi.App.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String gender;
    @NotNull
    private String phoneNumber;
    private String address;
    private LocalDate dateOfBirth;
}
