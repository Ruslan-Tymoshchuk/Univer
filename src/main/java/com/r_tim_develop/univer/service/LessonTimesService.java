package com.r_tim_develop.univer.service;

import java.util.List;

import com.r_tim_develop.univer.dto.LessonTimeDto;

public interface LessonTimesService {

    List<LessonTimeDto> findAllLessonTimes();

    void createLessonTime(LessonTimeDto lessonTimeDto);

    LessonTimeDto getLessonTimeById(Integer id);

    void updateLessonTime(LessonTimeDto lessonTimeDto);

    void deleteLessonTimeById(Integer id);
    
}