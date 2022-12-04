package com.r_tim_develop.univer.service;

import java.util.List;

import com.r_tim_develop.univer.dto.HolidayDto;

public interface HolidayService {

    List<HolidayDto> findAllHolidays();

    void createHoliday(HolidayDto holidayDto);

    HolidayDto getHolidayById(Integer id);

    HolidayDto findHolidayByName(String name);

    void updateHoliday(HolidayDto holidayDto);

    void deleteHolidayById(Integer id);

}