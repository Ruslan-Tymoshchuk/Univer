package com.r_tim_develop.univer.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.r_tim_develop.univer.dto.StudentDto;

public interface StudentService {

    Page<StudentDto> findPageWithStudents(Pageable pageable);
    
    List<StudentDto> findAllStudents();

    void createStudent(StudentDto studentdto);

    StudentDto getStudentById(int id);

    void updateStudent(StudentDto studentdto);

    void deleteStudentById(Integer id);
   
}