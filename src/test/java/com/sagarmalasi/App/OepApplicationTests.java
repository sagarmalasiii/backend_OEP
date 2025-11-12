package com.sagarmalasi.App;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.dto.InstituteDTO;
import com.sagarmalasi.App.dto.StudentDTO;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.entity.Student;
import com.sagarmalasi.App.service.InstituteService;
import com.sagarmalasi.App.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class OepApplicationTests {
    @Autowired
    private InstituteService instituteService;

    @Autowired
    private StudentService studentService;

	@Test
	void contextLoads() {
	}

    @Test
    public void registerInstitute(){
        InstituteDTO institute = InstituteDTO.builder()
                .instituteName("Nepal Engineering Council")
                .instituteCode("NEC-102")
                .email("nec@gmail.com")
                .phoneNumber("9800112233")
                .address("Thapathali")
                .build();
        InstituteDTO instituteDTO = instituteService.registerInstitute(institute);
        System.out.println(instituteDTO);
    }

    @Test
    public void registerStudent(){
        StudentDTO student = StudentDTO.builder()
                .name("Sagar Malasi")
                .email("sagarmalasi@gmail.com")
                .gender("MALE")
                .phoneNumber("9869404673")
                .dateOfBirth(LocalDate.of(2003,7,23))
                .address("Dhangadhi")
                .build();
        StudentDTO studentDTO = studentService.registerStudent(student);
        System.out.println(studentDTO);
    }






}
