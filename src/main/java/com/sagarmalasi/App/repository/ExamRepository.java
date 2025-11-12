package com.sagarmalasi.App.repository;

import com.sagarmalasi.App.entity.Exam;
import com.sagarmalasi.App.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    boolean existsByExamCode(String code);

    List<Exam> findByInstitute(Institute institute);

    List<Exam> findByExamCode(String code);

    @Query("SELECT e FROM Exam e WHERE e.examStatus = 'REGISTRATION_OPEN' ORDER BY e.examStartTime ASC")
    List<Exam> findALlRegistrationOpenExams();


}
