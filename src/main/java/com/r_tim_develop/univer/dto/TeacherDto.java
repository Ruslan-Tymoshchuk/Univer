package com.r_tim_develop.univer.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.r_tim_develop.univer.model.AcademicDegree;
import com.r_tim_develop.univer.model.Gender;

public class TeacherDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private AddressDto address;
    private AcademicDegree academicDegree;
    private List<CourseDto> courses = new ArrayList<>();
    private List<VacationDto> vacations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDto> courses) {
        this.courses = courses;
    }

    public List<VacationDto> getVacations() {
        return vacations;
    }

    public void setVacations(List<VacationDto> vacations) {
        this.vacations = vacations;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicDegree, address, birthDate, email, firstName, gender, id, lastName, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TeacherDto other = (TeacherDto) obj;
        return academicDegree == other.academicDegree && Objects.equals(address, other.address)
                && Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && gender == other.gender && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone);
    }
}