package com.r_tim_develop.univer.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import com.r_tim_develop.univer.model.Gender;

public class StudentDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String phone;
    private String email;
    private AddressDto address;
    private GroupDto group;

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

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

    public Integer getAge() {
        return Period.between(this.birthDate, LocalDate.now()).getYears();
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, birthDate, email, firstName, gender, group, id, lastName, phone);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentDto other = (StudentDto) obj;
        return Objects.equals(address, other.address) && Objects.equals(birthDate, other.birthDate)
                && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
                && gender == other.gender && Objects.equals(group, other.group) && Objects.equals(id, other.id)
                && Objects.equals(lastName, other.lastName) && Objects.equals(phone, other.phone);
    }
}