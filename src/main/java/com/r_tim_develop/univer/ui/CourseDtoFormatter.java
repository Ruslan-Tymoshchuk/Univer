package com.r_tim_develop.univer.ui;

import java.util.Locale;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import com.r_tim_develop.univer.dto.CourseDto;

public class CourseDtoFormatter implements Formatter<CourseDto> {

    @Override
    public CourseDto parse(String text, Locale locale) throws ParseException {
        CourseDto course = new CourseDto();
        if (text != null) {
            String[] parts = text.split(",");
            course.setId(Integer.parseInt(parts[0]));
            if (parts.length > 1) {
                course.setName(parts[1]);
            }
        }
        return course;
    }

    @Override
    public String print(CourseDto courseDto, Locale locale) {
        return courseDto.toString();
    }
}