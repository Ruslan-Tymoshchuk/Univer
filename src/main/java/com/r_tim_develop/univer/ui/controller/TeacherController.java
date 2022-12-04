package com.r_tim_develop.univer.ui.controller;

import static org.springframework.data.domain.PageRequest.of;
import java.util.List;
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
import com.r_tim_develop.univer.dto.TeacherDto;
import com.r_tim_develop.univer.dto.VacationDto;
import com.r_tim_develop.univer.service.CourseService;
import com.r_tim_develop.univer.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private static Logger logger = LoggerFactory.getLogger(TeacherController.class);

    public static final String PAGE_TEACHERS = "redirect:/teachers/";
    public static final String PAGE_TEACHER_MAIN = "teacher/teacher_main";
    public static final String PAGE_TEACHER_CARD = "teacher/teacher_card";

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String getPageWithTeachers(TeacherDto teacherDto, Model model, @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {
        logger.info("GET Method getPageWithTeachers was called");
        int currentPage = pageNumber.orElse(1);
        int currentSize = pageSize.orElse(5);
        Page<TeacherDto> pageWithTeachers = teacherService.findPageWithTeachers(of(currentPage - 1, currentSize));
        List<TeacherDto> listTeachers = pageWithTeachers.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageWithTeachers.getTotalPages());
        model.addAttribute("totalItems", pageWithTeachers.getTotalElements());
        model.addAttribute("listTeachers", listTeachers);
        model.addAttribute("allCourses", courseService.findAllCourses());
        return PAGE_TEACHER_MAIN;
    }

    @GetMapping("/{id}")
    public String getTeacherById(@PathVariable Integer id, VacationDto vacationDto, Model model) {
        logger.info("GET Method getTeacherById was called");
        model.addAttribute("teacherDto", teacherService.getTeacherById(id));
        model.addAttribute("allCourses", courseService.findAllCourses());
        return PAGE_TEACHER_CARD;
    }

    @PostMapping("/save")
    public String saveNewTeacher(TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);
        return PAGE_TEACHERS;
    }

    @PatchMapping("/update")
    public String updateTeacher(TeacherDto teacherDto) {
        logger.info("PATCH Method updateTeacher was called");
        teacherService.updateTeacher(teacherDto);
        return PAGE_TEACHERS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacherById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteTeacherById was called");
        teacherService.deleteTeacherById(id);
        return PAGE_TEACHERS;
    }
}