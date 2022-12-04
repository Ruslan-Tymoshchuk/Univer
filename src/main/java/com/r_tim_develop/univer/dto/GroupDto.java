package com.r_tim_develop.univer.dto;

import java.util.List;
import java.util.Objects;
import com.r_tim_develop.univer.model.Student;

public class GroupDto {

    private Integer id;
    private String name;
    private List<Student> students;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GroupDto other = (GroupDto) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(students, other.students);
    }
}