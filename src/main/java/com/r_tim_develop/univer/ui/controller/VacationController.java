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
import com.r_tim_develop.univer.dto.VacationDto;
import com.r_tim_develop.univer.service.VacationService;

@Controller
@RequestMapping("/vacations")
public class VacationController {

    private static Logger logger = LoggerFactory.getLogger(VacationController.class);

    public static final String PAGE_TEACHERS = "redirect:/teachers/";
    public static final String PAGE_VACATION_MAIN = "vacation/vacation_main";
    public static final String PAGE_VACATION_CARD = "vacation/vacation_card";

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/{id}")
    public String getVacationById(@PathVariable("id") Integer id, Model model) {
        logger.info("GET Method getCourseById was called");
        model.addAttribute("vacationDto", vacationService.getVacationById(id));
        return PAGE_VACATION_CARD;
    }

    @PostMapping("/save")
    public String saveNewVacation(VacationDto vacationDto) {
        logger.info("POST Method saveNewVacation was called");
        vacationService.createVacation(vacationDto);
        return PAGE_TEACHERS;
    }

    @PatchMapping("/update")
    public String updateVacation(VacationDto vacationDto) {
        logger.info("PATCH Method updateVacation was called");
        vacationService.updateVacation(vacationDto);
        return PAGE_TEACHERS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVacationById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteVacationById was called");
        vacationService.deleteVacationById(id);
        return PAGE_TEACHERS;
    }

    @GetMapping("/by-teacher-and-date-interval")
    public String getVacationsByTeacher(@RequestParam("start") LocalDate startDate,
            @RequestParam("end") LocalDate endDate, Model model) {
        logger.info("GET Method getVacationsByTeacher was called");
        model.addAttribute("vacations", vacationService.findVacationsByDateInterval(startDate, endDate));
        return PAGE_VACATION_MAIN;
    }
}