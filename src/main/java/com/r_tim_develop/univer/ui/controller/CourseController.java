package com.r_tim_develop.univer.ui.controller;

import java.util.List;
import static org.springframework.data.domain.PageRequest.of;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.r_tim_develop.univer.dto.CourseDto;
import com.r_tim_develop.univer.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static Logger logger = LoggerFactory.getLogger(CourseController.class);

    public static final String PAGE_COURSES = "redirect:/courses/";
    public static final String PAGE_NEW_COURSE = "course/new_course";
    public static final String PAGE_COURSE_MAIN = "course/course_main";
    public static final String PAGE_COURSE_CARD = "course/course_card";

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String getPageWithCourses(CourseDto courseDto, Model model, @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {
        logger.info("GET Method getPageWithCourses was called");
        int currentPage = pageNumber.orElse(1);
        int currentSize = pageSize.orElse(5);
        Page<CourseDto> pageWithCourses = courseService.findPageWithCourses(of(currentPage - 1, currentSize));
        List<CourseDto> listCourses = pageWithCourses.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageWithCourses.getTotalPages());
        model.addAttribute("totalItems", pageWithCourses.getTotalElements());
        model.addAttribute("listCourses", listCourses);
        return PAGE_COURSE_MAIN;
    }

    @GetMapping("/{id}")
    public String getCourseById(@PathVariable("id") int id, Model model) {
        logger.info("GET Method getCourseById was called");
        model.addAttribute("courseDto", courseService.getCourseById(id));
        return PAGE_COURSE_CARD;
    }

    @PostMapping("/save")
    public String saveNewCourse(CourseDto courseDto) {
        logger.info("POST Method saveNewCourse was called");
        courseService.createCourse(courseDto);
        return PAGE_COURSES;
    }

    @PatchMapping("/update")
    public String updateCourse(CourseDto courseDto) {
        logger.info("PATCH Method updateCourse was called");
        courseService.updateCourse(courseDto);
        return PAGE_COURSES;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteCourseById was called");
        courseService.deleteCourseById(id);
        return PAGE_COURSES;
    }
}