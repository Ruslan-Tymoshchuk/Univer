package com.r_tim_develop.univer.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LessonDto {

    private Integer id;
    private TeacherDto teacher;
    private CourseDto course;
    private AudienceDto audience;
    private LocalDate date;
    private LessonTimeDto time;
    private List<GroupDto> groups;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public AudienceDto getAudience() {
        return audience;
    }

    public void setAudience(AudienceDto audience) {
        this.audience = audience;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LessonTimeDto getTime() {
        return time;
    }

    public void setTime(LessonTimeDto time) {
        this.time = time;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

    @Override
    public int hashCode() {
        return Objects.hash(audience, course, date, id, teacher, time);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LessonDto other = (LessonDto) obj;
        return Objects.equals(audience, other.audience) && Objects.equals(course, other.course)
                && Objects.equals(date, other.date) && Objects.equals(id, other.id)
                && Objects.equals(teacher, other.teacher) && Objects.equals(time, other.time);
    }
}