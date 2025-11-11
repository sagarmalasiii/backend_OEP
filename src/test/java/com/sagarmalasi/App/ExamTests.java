package com.sagarmalasi.App;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.entity.enums.ExamStatus;
import com.sagarmalasi.App.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ExamTests {
    @Autowired
    private ExamService examService;

    @Test
    public void testExamCreation(){
        ExamDTO dto = ExamDTO.builder()
                .title("Engineering Entrace Exam 2025")
                .examCode("IOE-201")
                .details("This is a 100 question examination..")
                .startRegistrationTime(LocalDateTime.of(2025,11,12,12,0))
                .endRegistrationTime(LocalDateTime.of(2025,11,14,10,20))
                .examStartTime(LocalDateTime.of(2025,11,15,2,0))
                .examEndTime(LocalDateTime.of(2025,11,15,3,0))
                .instituteId(101L)
                .examStatus(ExamStatus.REGISTRATION_OPEN)
                .build();

        ExamDTO responseDto = examService.createExam(dto);
        System.out.println(responseDto);


    }
}
