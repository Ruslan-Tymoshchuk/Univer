package com.r_tim_develop.univer.ui;

import java.util.Locale;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import com.r_tim_develop.univer.dto.GroupDto;

public class GroupDtoFormatter implements Formatter<GroupDto> {

    @Override
    public GroupDto parse(String text, Locale locale) throws ParseException {
        GroupDto group = new GroupDto();
        if (text != null) {
            String[] parts = text.split(",");
            group.setId(Integer.parseInt(parts[0]));
            if (parts.length > 1) {
                group.setName(parts[1]);
            }
        }
        return group;
    }

    @Override
    public String print(GroupDto groupDto, Locale locale) {
        return groupDto.toString();
    }
}