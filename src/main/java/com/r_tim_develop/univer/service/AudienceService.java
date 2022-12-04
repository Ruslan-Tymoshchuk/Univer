package com.r_tim_develop.univer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.r_tim_develop.univer.dto.AudienceDto;

public interface AudienceService {

    Page<AudienceDto> findPageWithAudiences(Pageable pageable);
    
    List<AudienceDto> findAllAudiences();

    void createAudience(AudienceDto audienceDto);

    AudienceDto getAudienceById(Integer id);

    void updateAudience(AudienceDto audienceDto);

    void deleteAudienceById(Integer id);
   
}