package com.sagarmalasi.App.service;

import com.sagarmalasi.App.dto.StudentDTO;
import com.sagarmalasi.App.entity.Student;
import com.sagarmalasi.App.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream().map((element) -> modelMapper.map(element, StudentDTO.class)).toList();

    }

    public StudentDTO getStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow();
        return modelMapper.map(student,StudentDTO.class);
    }

    public StudentDTO registerStudent(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO,Student.class);
        Student newStudent = studentRepository.save(student);
        return modelMapper.map(newStudent,StudentDTO.class);

    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);

    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO){
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student Not Found"));
        modelMapper.map(studentDTO,student);

        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent,StudentDTO.class);

    }
}
