package com.r_tim_develop.univer.ui.controller;

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
import com.r_tim_develop.univer.dto.LessonTimeDto;
import com.r_tim_develop.univer.service.LessonTimesService;

@Controller
@RequestMapping("/lesson-times")
public class LessonTimesController {

    private static Logger logger = LoggerFactory.getLogger(LessonTimesController.class);

    public static final String PAGE_LESSON_TIME_MAIN = "lesson_time/lesson_times_main";
    public static final String PAGE_LESSON_TIME_CARD = "lesson_time/lesson_time_card";
    public static final String PAGE_LESSON_REDIRECT = "redirect:/lessons/";

    private final LessonTimesService lessonTimesService;

    public LessonTimesController(LessonTimesService lessonTimesService) {
        this.lessonTimesService = lessonTimesService;
    }

    @GetMapping("/")
    public String getAllLessonTimes(Model model) {
        logger.info("GET Method getAllLessonTimes was called");
        model.addAttribute("allLessonTimes", lessonTimesService.findAllLessonTimes());
        return PAGE_LESSON_TIME_MAIN;
    }

    @GetMapping("/{id}")
    public String getLessonTimeById(@PathVariable("id") Integer id, Model model) {
        logger.info("GET Method getLessonTimeById was called");
        model.addAttribute("lessonTimeDto", lessonTimesService.getLessonTimeById(id));
        return PAGE_LESSON_TIME_CARD;
    }

    @PostMapping("/save")
    public String saveNewLessonTime(LessonTimeDto lessonTimeDto) {
        logger.info("POST Method saveNewLessonTime was called");
        lessonTimesService.createLessonTime(lessonTimeDto);
        return PAGE_LESSON_REDIRECT;
    }

    @PatchMapping("/update")
    public String updateLessonTime(LessonTimeDto lessonTimeDto) {
        logger.info("PATCH Method updateLessonTime was called");
        lessonTimesService.updateLessonTime(lessonTimeDto);
        return PAGE_LESSON_REDIRECT;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLessonTimeById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteLessonTimeById was called");
        lessonTimesService.deleteLessonTimeById(id);
        return PAGE_LESSON_REDIRECT;
    }
}