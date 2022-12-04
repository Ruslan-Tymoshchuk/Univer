package com.r_tim_develop.univer.ui.controller;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.r_tim_develop.univer.dto.LessonDto;
import com.r_tim_develop.univer.dto.LessonTimeDto;
import com.r_tim_develop.univer.service.AudienceService;
import com.r_tim_develop.univer.service.CourseService;
import com.r_tim_develop.univer.service.GroupService;
import com.r_tim_develop.univer.service.LessonService;
import com.r_tim_develop.univer.service.LessonTimesService;
import com.r_tim_develop.univer.service.TeacherService;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private static Logger logger = LoggerFactory.getLogger(LessonController.class);

    public static final String PAGE_LESSONS = "redirect:/lessons/";
    public static final String PAGE_NEW_LESSON = "lesson/new_lesson";
    public static final String PAGE_LESSON_MAIN = "lesson/lesson_main";
    public static final String PAGE_LESSON_CARD = "lesson/lesson_card";
    public static final String PAGE_LESSONS_BY_DATE = "lesson/lessons_by_date_interval";

    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final AudienceService audienceService;
    private final LessonTimesService lessonTimeService;
    private final GroupService groupService;

    public LessonController(LessonService lessonService, TeacherService teacherService, CourseService courseService,
            AudienceService audienceService, LessonTimesService lessonTimeService, GroupService groupService) {
        this.lessonService = lessonService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.audienceService = audienceService;
        this.lessonTimeService = lessonTimeService;
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String getAllLessons(Model model, LessonDto lessonDto, LessonTimeDto lessonTimeDto) {
        logger.info("GET Method getAllLessons was called");
        model.addAttribute("allTeachers", teacherService.findAllTeachers());
        model.addAttribute("allCourses", courseService.findAllCourses());
        model.addAttribute("allAudiences", audienceService.findAllAudiences());
        model.addAttribute("allLessonTimes", lessonTimeService.findAllLessonTimes());
        model.addAttribute("allGroups", groupService.findAllGroups());
        model.addAttribute("allLessons", lessonService.findAllLessons());
        return PAGE_LESSON_MAIN;
    }

    @GetMapping("/{id}")
    public String getLessonById(@PathVariable("id") int id, Model model) {
        logger.info("GET Method getLessonById was called");
        model.addAttribute("lessonDto", lessonService.getLessonById(id));
        model.addAttribute("allTeachers", teacherService.findAllTeachers());
        model.addAttribute("allCourses", courseService.findAllCourses());
        model.addAttribute("allAudiences", audienceService.findAllAudiences());
        model.addAttribute("allLessonTimes", lessonTimeService.findAllLessonTimes());
        model.addAttribute("allGroups", groupService.findAllGroups());
        return PAGE_LESSON_CARD;
    }

    @PostMapping("/save")
    public String saveNewLesson(LessonDto lessonDto) {
        logger.info("POST Method saveNewLesson was called");
        lessonService.createLesson(lessonDto);
        return PAGE_LESSONS;
    }

    @PatchMapping("/update")
    public String updateLesson(LessonDto lessonDto) {
        logger.info("PATCH Method updateLesson was called");
        lessonService.updateLesson(lessonDto);
        return PAGE_LESSONS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLessonById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteLessonById was called");
        lessonService.deleteLessonById(id);
        return PAGE_LESSONS;
    }

    @GetMapping("/by-student-group-and-date-interval")
    public String getLessonsByGroupDateInterval(@RequestParam("id") Integer id,
            @RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate, Model model) {
        logger.info("GET Method getLessonsByGroupDateInterval was called");
        model.addAttribute("lessons", lessonService.findByGroupIdAndDateInterval(id, startDate, endDate));
        return PAGE_LESSONS_BY_DATE;
    }

    @GetMapping("/by-teacher-id-and-date-interval")
    public String getLessonsByTeacherAndDateInterval(@RequestParam("id") Integer id,
            @RequestParam("start") LocalDate startDate, @RequestParam("end") LocalDate endDate, Model model) {
        logger.info("GET Method getLessonsByTeacherAndDateInterval was called");
        model.addAttribute("lessons", lessonService.findByTeacherAndDateInterval(id, startDate, endDate));
        return PAGE_LESSONS_BY_DATE;
    }
}