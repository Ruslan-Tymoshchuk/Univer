package com.r_tim_develop.univer.dto;

import java.time.LocalDate;
import java.util.Objects;

public class VacationDto {

    private Integer id;
    private TeacherDto teacherDto;
    private LocalDate startDate;
    private LocalDate endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, id, startDate, teacherDto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VacationDto other = (VacationDto) obj;
        return Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
                && Objects.equals(startDate, other.startDate) && Objects.equals(teacherDto, other.teacherDto);
    }
}