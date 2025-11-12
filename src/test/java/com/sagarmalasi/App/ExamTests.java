package com.sagarmalasi.App;

import com.sagarmalasi.App.dto.ExamDTO;

import com.sagarmalasi.App.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ExamTests {
    @Autowired
    private ExamService examService;

    @Test
    public void testExamCreation(){
        ExamDTO dto = ExamDTO.builder()
                .title("CSIT Entrance")
                .examCode("CSIT-2025")
                .details("This is a 2 question examination..")
                .startRegistrationTime(LocalDateTime.of(2025,11,13,12,0))
                .endRegistrationTime(LocalDateTime.of(2025,11,14,10,20))
                .examStartTime(LocalDateTime.of(2025,11,15,2,0))
                .examEndTime(LocalDateTime.of(2025,11,15,3,0))
                .instituteId(2L)

                .build();

        ExamDTO responseDto = examService.createExam(dto);
        System.out.println(responseDto);


    }

    @Test
    public void getAllOpenExams(){
        List<ExamDTO> exams= examService.findAllOpenExams();
        exams.forEach(exam -> System.out.println(exam));

    }
}
