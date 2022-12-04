package com.r_tim_develop.univer.service.impl;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.r_tim_develop.univer.dto.AudienceDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.AudienceRepository;
import com.r_tim_develop.univer.service.AudienceService;

@Service
@Transactional(readOnly = true)
public class AudienceServiceImpl implements AudienceService {

    public static final Logger logger = LoggerFactory.getLogger(AudienceServiceImpl.class);

    private final AudienceRepository audienceRepository;
    private final MapStructMapper audienceMapper;

    public AudienceServiceImpl(AudienceRepository audienceRepository, MapStructMapper audienceMapper) {
        this.audienceRepository = audienceRepository;
        this.audienceMapper = audienceMapper;
    }

    @Override
    public Page<AudienceDto> findPageWithAudiences(Pageable pageable) {
        logger.info("In CourseService findPageWithAudiences");
        try {
            return audienceRepository.findAll(pageable).map(audienceMapper::audienceToAudienceDto);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<AudienceDto> findAllAudiences() {
        logger.info("In AudienceService findAll");
        try {
            return audienceRepository.findAll().stream().map(audienceMapper::audienceToAudienceDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createAudience(AudienceDto audienceDto) {
        logger.info("In AudienceService create {}", audienceDto);
        try {
            audienceRepository.save(audienceMapper.audienceDtoToAudience(audienceDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public AudienceDto getAudienceById(Integer id) {
        logger.info("In AudienceService getById {}", id);
        try {
            return audienceMapper.audienceToAudienceDto(audienceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateAudience(AudienceDto audienceDto) {
        logger.info("In AudienceService update {}", audienceDto);
        try {
            audienceRepository.save(audienceMapper.audienceDtoToAudience(audienceDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteAudienceById(Integer id) {
        logger.info("In AudienceService delete {}", id);
        try {
            audienceRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}