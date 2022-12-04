package com.r_tim_develop.univer.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.r_tim_develop.univer.dto.TeacherDto;

public interface TeacherService {

    Page<TeacherDto> findPageWithTeachers(Pageable pageable);
    
    List<TeacherDto> findAllTeachers();

    void createTeacher(TeacherDto teacherdto);

    TeacherDto getTeacherById(int id);

    void updateTeacher(TeacherDto teacherdto);

    void deleteTeacherById(Integer id);

  }