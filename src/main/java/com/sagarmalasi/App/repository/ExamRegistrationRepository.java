package com.sagarmalasi.App.repository;

import com.sagarmalasi.App.entity.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration,Long> {
    boolean existsByStudent_IdAndExam_Id(Long studentId,Long examId);



}
