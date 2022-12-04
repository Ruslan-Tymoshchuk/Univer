package com.r_tim_develop.univer.dto;

import java.util.Objects;

public class AudienceDto {

    private Integer id;
    private Integer number;
    private Integer capacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, id, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AudienceDto other = (AudienceDto) obj;
        return Objects.equals(capacity, other.capacity) && Objects.equals(id, other.id)
                && Objects.equals(number, other.number);
    }
}