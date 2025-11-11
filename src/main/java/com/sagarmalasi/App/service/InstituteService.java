package com.sagarmalasi.App.service;

import com.sagarmalasi.App.dto.InstituteDTO;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.repository.InstituteRepository;
import com.sagarmalasi.App.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituteService {
    private final InstituteRepository instituteRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    public List<InstituteDTO> getAllInstitutes(){
        List<Institute> institutes = instituteRepository.findAll();
        return institutes.stream().map((element) -> modelMapper.map(element, InstituteDTO.class)).toList();

    }

    public InstituteDTO getInstituteById(Long id){
        Institute  institute = instituteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Institute Not Found With id: "+id));
        return modelMapper.map(institute, InstituteDTO.class);

    }

    public InstituteDTO registerInstitute(InstituteDTO instituteDTO){
        Institute institute = modelMapper.map(instituteDTO,Institute.class);
        Institute newInstitute = instituteRepository.save(institute);
        return modelMapper.map(newInstitute,InstituteDTO.class);

    }

    public void deleteInstitute(Long id){
        studentRepository.deleteById(id);
    }

    public InstituteDTO updateInstitute(Long id, InstituteDTO instituteDTO) {
        Institute institute = instituteRepository.findById(id).orElseThrow();
        modelMapper.map(instituteDTO,institute);
        Institute updatedInstitute = instituteRepository.save(institute);
        return modelMapper.map(updatedInstitute,InstituteDTO.class);



    }

}
