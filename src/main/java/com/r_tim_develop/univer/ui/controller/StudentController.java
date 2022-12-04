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
import com.r_tim_develop.univer.dto.StudentDto;
import com.r_tim_develop.univer.service.GroupService;
import com.r_tim_develop.univer.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    public static final String PAGE_STUDENTS = "redirect:/students/";
    public static final String PAGE_STUDENT_MAIN = "student/student_main";
    public static final String PAGE_STUDENT_CARD = "student/student_card";

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String getPageWithStudents(StudentDto studentDto, Model model, @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {
        logger.info("GET Method getPageWithStudents was called");
        int currentPage = pageNumber.orElse(1);
        int currentSize = pageSize.orElse(5);
        Page<StudentDto> pageWithStudents = studentService.findPageWithStudents(of(currentPage - 1, currentSize));
        List<StudentDto> listStudents = pageWithStudents.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageWithStudents.getTotalPages());
        model.addAttribute("totalItems", pageWithStudents.getTotalElements());
        model.addAttribute("listStudents", listStudents);
        model.addAttribute("allGroups", groupService.findAllGroups());
        return PAGE_STUDENT_MAIN;
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable("id") Integer id, Model model) {
        logger.info("GET Method getStudentById was called");
        model.addAttribute("studentDto", studentService.getStudentById(id));
        model.addAttribute("allGroups", groupService.findAllGroups());
        return PAGE_STUDENT_CARD;
    }

    @PostMapping("/save")
    public String saveNewStudent(StudentDto studentDto) {
        logger.info("POST Method saveNewStudent was called");
        studentService.createStudent(studentDto);
        return PAGE_STUDENTS;
    }

    @PatchMapping("/update")
    public String updateStudent(StudentDto studentDto) {
        logger.info("PATCH Method updateStudent was called");
        studentService.updateStudent(studentDto);
        return PAGE_STUDENTS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteStudentById was called");
        studentService.deleteStudentById(id);
        return PAGE_STUDENTS;
    }
}