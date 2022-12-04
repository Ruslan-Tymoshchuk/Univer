package com.r_tim_develop.univer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r_tim_develop.univer.model.Audience;

@Repository
public interface AudienceRepository extends JpaRepository<Audience, Integer> {

    Page<Audience> findAll(Pageable pageable);

}