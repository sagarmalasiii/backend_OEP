package com.sagarmalasi.App.service;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.entity.Exam;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.entity.enums.ExamStatus;
import com.sagarmalasi.App.repository.ExamRepository;
import com.sagarmalasi.App.repository.InstituteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final InstituteRepository instituteRepository;


    @Transactional
    public ExamDTO createExam(ExamDTO examDTO) {

        if (examRepository.existsByExamCode(examDTO.getExamCode())) {
            throw new IllegalArgumentException("Exam code exists: " + examDTO.getExamCode());
        }

        Institute institute = instituteRepository.findById(examDTO.getInstituteId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Institute ID: " + examDTO.getInstituteId()));

        Exam exam = modelMapper.map(examDTO, Exam.class);
        exam.setInstitute(institute);

        LocalDateTime now = LocalDateTime.now();

        if (now.isBefore(exam.getStartRegistrationTime())) {
            exam.setExamStatus(ExamStatus.DRAFT);
        } else if (now.isBefore(exam.getEndRegistrationTime())) {
            exam.setExamStatus(ExamStatus.REGISTRATION_OPEN);
        } else if (now.isBefore(exam.getExamStartTime())) {
            exam.setExamStatus(ExamStatus.REGISTRATION_CLOSED);
        } else if (now.isBefore(exam.getExamEndTime())) {
            exam.setExamStatus(ExamStatus.ACTIVE);
        } else {
            exam.setExamStatus(ExamStatus.COMPLETED);
        }

        Exam newExam = examRepository.save(exam);

        ExamDTO response = modelMapper.map(newExam, ExamDTO.class);
        response.setDurationInMinutes(newExam.getDurationInMinutes());
        return response;
    }


    public List<ExamDTO> findAllOpenExams(){
        List<Exam> exams = examRepository.findALlRegistrationOpenExams();
        return exams.stream().map((element) -> modelMapper.map(element, ExamDTO.class)).toList();
    }

}
