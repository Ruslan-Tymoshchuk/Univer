package com.r_tim_develop.univer.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.r_tim_develop.univer.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Page<Course> findAll(Pageable pageable);

    @Query(value = "select t.courses from Teacher t where t.id = :id")
    List<Course> findByTeacher(@Param("id") int teacherId);

}