package com.r_tim_develop.univer.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HolidayDto {

    private String id;

    @JsonProperty(value = "title")
    private String name;

    @JsonProperty(value = "start")
    private String startDate;

    @JsonProperty(value = "end")
    private String endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, id, name, startDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HolidayDto other = (HolidayDto) obj;
        return Objects.equals(endDate, other.endDate) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name) && Objects.equals(startDate, other.startDate);
    }
}