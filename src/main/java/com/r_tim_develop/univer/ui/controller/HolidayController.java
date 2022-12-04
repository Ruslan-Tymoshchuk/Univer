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
import org.springframework.web.bind.annotation.RequestParam;
import com.r_tim_develop.univer.dto.HolidayDto;
import com.r_tim_develop.univer.service.HolidayService;

@Controller
@RequestMapping("/holidays")
public class HolidayController {

    private static Logger logger = LoggerFactory.getLogger(HolidayController.class);

    public static final String PAGE_HOLIDAYS = "redirect:/holidays/";
    public static final String PAGE_HOLIDAY_MAIN = "holiday/holiday_main";
    public static final String PAGE_HOLIDAY_CARD = "holiday/holiday_card";

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/")
    public String getAllHolidays(Model model, HolidayDto holidayDto) {
        logger.info("GET Method getAllHolidays was called");
        return PAGE_HOLIDAY_MAIN;
    }

    @GetMapping("/name")
    public String getHolidayByName(@RequestParam("holidayname") String name, Model model) {
        logger.info("GET Method getGroupById was called");
        model.addAttribute("holidayDto", holidayService.findHolidayByName(name));
        return PAGE_HOLIDAY_CARD;
    }

    @PostMapping("/save")
    public String saveNewHoliday(HolidayDto holidayDto) {
        logger.info("POST Method saveNewHoliday was called");
        holidayService.createHoliday(holidayDto);
        return PAGE_HOLIDAYS;
    }

    @PatchMapping("/update")
    public String updateHoliday(HolidayDto holidayDto) {
        logger.info("PATCH Method updateHoliday was called");
        holidayService.updateHoliday(holidayDto);
        return PAGE_HOLIDAYS;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHolidayById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteHolidayById was called");
        holidayService.deleteHolidayById(id);
        return PAGE_HOLIDAYS;
    }
}