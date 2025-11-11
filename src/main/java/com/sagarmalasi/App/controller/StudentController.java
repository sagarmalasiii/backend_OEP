package com.sagarmalasi.App.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping()
    public List<String> nameOfALlStudents() {
        String[] students = {"Sagar Malasi","Smirti Bagale","Rakesh Joshi"};
        return Arrays.stream(students).toList();
    }
}
