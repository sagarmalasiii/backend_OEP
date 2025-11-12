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
import java.util.stream.Collectors;

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
        if (exam.getExamStatus() == null) {
            exam.setExamStatus(ExamStatus.DRAFT);
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

    public List<ExamDTO> getAllExams(Long instituteId){
        Institute institute = instituteRepository.findById(instituteId).orElseThrow();


        List<Exam> exams = examRepository.findByInstitute(institute);
        return exams.stream().map((element) -> modelMapper.map(element, ExamDTO.class)).collect(Collectors.toList());

    }

}
