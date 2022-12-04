package com.r_tim_develop.univer.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.r_tim_develop.univer.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    @Query(value = "SELECT l.* FROM lessons l LEFT JOIN lessons_groups lg ON lg.lesson_id = l.id "
            + "WHERE group_id = :id AND date between :start and :end", nativeQuery = true)
    List<Lesson> findByGroupIdAndDateInterval(@Param("id") int groupId, @Param("start") LocalDate startDate,
            @Param("end") LocalDate endDate);

    @Query(value = "SELECT l FROM Lesson l WHERE l.teacher.id = :id AND date between :start and :end")
    List<Lesson> findByTeacherAndDateInterval(@Param("id") int teacherId, @Param("start") LocalDate startDate,
            @Param("end") LocalDate endDate);
}