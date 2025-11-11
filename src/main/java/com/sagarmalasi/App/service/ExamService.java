package com.sagarmalasi.App.service;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.entity.Exam;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.repository.ExamRepository;
import com.sagarmalasi.App.repository.InstituteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        Exam newExam = examRepository.save(exam);
        ExamDTO response = modelMapper.map(newExam, ExamDTO.class);
        response.setDurationInMinutes(newExam.getDurationInMinutes());
        return response;
    }

}
