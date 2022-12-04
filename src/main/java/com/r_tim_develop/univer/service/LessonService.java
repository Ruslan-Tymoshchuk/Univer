package com.r_tim_develop.univer.service;

import java.time.LocalDate;
import java.util.List;

import com.r_tim_develop.univer.dto.LessonDto;

public interface LessonService {

    List<LessonDto> findAllLessons();

    void createLesson(LessonDto lessonDto);

    LessonDto getLessonById(Integer id);

    void updateLesson(LessonDto lessonDto);

    void deleteLessonById(Integer id);

    List<LessonDto> findByGroupIdAndDateInterval(Integer id, LocalDate startDate, LocalDate endDate);

    List<LessonDto> findByTeacherAndDateInterval(Integer id, LocalDate startDate, LocalDate endDate);
    
}