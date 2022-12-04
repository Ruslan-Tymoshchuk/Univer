package com.r_tim_develop.univer.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.r_tim_develop.univer.model.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    @Query(value = "SELECT v FROM Vacation v WHERE start_date >= :start_date AND end_date <= :end_date")
    List<Vacation> findByDateInterval(@Param("start_date") LocalDate startDate, @Param("end_date") LocalDate endDate);

}