package com.r_tim_develop.univer.dto;

import org.mapstruct.Mapper;
import com.r_tim_develop.univer.model.Address;
import com.r_tim_develop.univer.model.Audience;
import com.r_tim_develop.univer.model.Course;
import com.r_tim_develop.univer.model.Group;
import com.r_tim_develop.univer.model.Holiday;
import com.r_tim_develop.univer.model.Lesson;
import com.r_tim_develop.univer.model.LessonTime;
import com.r_tim_develop.univer.model.Student;
import com.r_tim_develop.univer.model.Teacher;
import com.r_tim_develop.univer.model.Vacation;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);

    AudienceDto audienceToAudienceDto(Audience audience);

    Audience audienceDtoToAudience(AudienceDto audienceDto);

    CourseDto courseToCourseDto(Course course);

    Course courseDtoToCourse(CourseDto courseDto);

    GroupDto groupToGroupDto(Group group);

    Group groupDtoToGroup(GroupDto groupDto);

    HolidayDto holidayToHolidayDto(Holiday holiday);

    Holiday holidayDtoToHoliday(HolidayDto holidayDto);

    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);

    LessonTimeDto lessonTimeToHoLessonTimeDto(LessonTime lessonTime);

    LessonTime lessonTimeDtoToLessonTime(LessonTimeDto lessonTimeDto);

    StudentDto studentToStudentDto(Student student);

    Student studentDtoToStudent(StudentDto studentDto);

    TeacherDto teacherToTeacherDto(Teacher teacher);

    Teacher teacherDtoToTeacher(TeacherDto teacherDto);

    VacationDto vacationToVacationDto(Vacation vacation);

    Vacation vacationDtoToVacation(VacationDto vacationDto);
}