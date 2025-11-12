package com.sagarmalasi.App.controller;

import com.sagarmalasi.App.dto.ExamDTO;
import com.sagarmalasi.App.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
@CrossOrigin("http://localhost:5173/")
public class ExamController {
    private final ExamService examService;

    @PostMapping("/create")
    public ResponseEntity<ExamDTO> createExam(@RequestBody @Valid ExamDTO examDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(examDTO));

    }

    @GetMapping("/{instituteId}")
    public ResponseEntity<List<ExamDTO>> getAllExams(@PathVariable Long instituteId){
        return ResponseEntity.ok(examService.getAllExams(instituteId));
    }
}
