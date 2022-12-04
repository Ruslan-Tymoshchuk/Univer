package com.r_tim_develop.univer.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.r_tim_develop.univer.dto.HolidayDto;
import com.r_tim_develop.univer.service.HolidayService;

@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayRestController {

    private static Logger logger = LoggerFactory.getLogger(HolidayRestController.class);

    private final HolidayService holidayService;

    public HolidayRestController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/")
    public List<HolidayDto> getAllHolidays() {
        logger.info("GET Method getAllHolidays was called");
        return holidayService.findAllHolidays();
    }
}