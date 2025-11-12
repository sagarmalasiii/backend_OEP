package com.sagarmalasi.App.service;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.dto.ExamRegistrationDTO;
import com.sagarmalasi.App.entity.Exam;
import com.sagarmalasi.App.entity.ExamRegistration;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.entity.Student;
import com.sagarmalasi.App.entity.enums.RegistrationStatus;
import com.sagarmalasi.App.repository.ExamRegistrationRepository;
import com.sagarmalasi.App.repository.ExamRepository;
import com.sagarmalasi.App.repository.InstituteRepository;
import com.sagarmalasi.App.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamRegistrationService {
    private final ExamRegistrationRepository examRegistrationRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @Transactional
    public ExamRegistrationDTO registerForExam(ExamRegistrationDTO dto) {

        if (examRegistrationRepository.existsByStudent_IdAndExam_Id(dto.getStudentId(), dto.getExamId())) {
            throw new IllegalArgumentException("Student already registered for this exam");
        }

        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + dto.getStudentId()));

        Exam exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid exam ID: " + dto.getExamId()));

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(exam.getStartRegistrationTime()) || now.isAfter(exam.getEndRegistrationTime())) {
            throw new IllegalStateException("Registration not allowed at this time");
        }

        ExamRegistration registration = ExamRegistration.builder()
                .student(student)
                .exam(exam)
                .registrationStatus(RegistrationStatus.PENDING)
                .build();

        ExamRegistration saved = examRegistrationRepository.save(registration);
        return modelMapper.map(saved, ExamRegistrationDTO.class);
    }

}
