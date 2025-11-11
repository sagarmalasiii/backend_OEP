package com.sagarmalasi.App.repository;

import com.sagarmalasi.App.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute,Long> {
    boolean existsByEmail(String email);
    boolean existsByInstituteName(String name);
    boolean existsByInstituteCode(String instituteCode);
    boolean existsByPhoneNumber(String phoneNumber);

}
