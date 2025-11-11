package com.sagarmalasi.App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstituteDTO {
    private Long id;
    private String instituteName;
    private String email;
    private String instituteCode;
    private String address;
    private String phoneNumber;


}
