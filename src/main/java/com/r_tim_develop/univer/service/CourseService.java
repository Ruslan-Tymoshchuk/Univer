package com.r_tim_develop.univer.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.r_tim_develop.univer.dto.CourseDto;

public interface CourseService {

    Page<CourseDto> findPageWithCourses(Pageable pageable);

    List<CourseDto> findAllCourses();

    void createCourse(CourseDto courseDto);

    CourseDto getCourseById(Integer id);

    void updateCourse(CourseDto courseDto);

    void deleteCourseById(Integer id);

}