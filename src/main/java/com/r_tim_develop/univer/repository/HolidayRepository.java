package com.r_tim_develop.univer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.r_tim_develop.univer.model.Holiday;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

    Holiday findByName(String name);

}