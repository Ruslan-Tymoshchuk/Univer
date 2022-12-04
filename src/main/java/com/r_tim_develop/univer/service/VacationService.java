package com.r_tim_develop.univer.service;

import java.time.LocalDate;
import java.util.List;

import com.r_tim_develop.univer.dto.VacationDto;
import com.r_tim_develop.univer.model.Vacation;

public interface VacationService {

    List<VacationDto> findAllVacations();

    void createVacation(VacationDto vacationDto);

    VacationDto getVacationById(Integer id);

    void updateVacation(VacationDto vacationDto);

    void deleteVacationById(Integer id);

    List<Vacation> findVacationsByDateInterval(LocalDate startDate, LocalDate endDate);

}