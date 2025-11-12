package com.sagarmalasi.App;

import com.sagarmalasi.App.dto.ExamRegistrationDTO;
import com.sagarmalasi.App.service.ExamRegistrationService;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegisterTest {
    @Autowired
    private ExamRegistrationService examRegistrationService;
    @Test
    public void register(){
        ExamRegistrationDTO registrationDTO = ExamRegistrationDTO.builder()
                .studentId(1l)
                .examId(8l)
                .build();
        ExamRegistrationDTO examRegistrationDTO = examRegistrationService.registerForExam(registrationDTO);

        System.out.println("Exam Registration Sent Succesfully!!!");
    }
}
