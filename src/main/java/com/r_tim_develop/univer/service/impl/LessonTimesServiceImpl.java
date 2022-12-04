package com.r_tim_develop.univer.service.impl;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.r_tim_develop.univer.dto.LessonTimeDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.LessonTimeRepository;
import com.r_tim_develop.univer.service.LessonTimesService;

@Service
@Transactional(readOnly = true)
public class LessonTimesServiceImpl implements LessonTimesService {

    public static final Logger logger = LoggerFactory.getLogger(LessonTimesServiceImpl.class);

    private final LessonTimeRepository lessonTimeRepository;
    private final MapStructMapper lessonTimeMapper;

    public LessonTimesServiceImpl(LessonTimeRepository lessonTimeRepository, MapStructMapper lessonTimeMapper) {
        this.lessonTimeRepository = lessonTimeRepository;
        this.lessonTimeMapper = lessonTimeMapper;
    }

    @Override
    public List<LessonTimeDto> findAllLessonTimes() {
        logger.info("In LessonTimeService findAll");
        try {
            return lessonTimeRepository.findAll().stream().map(lessonTimeMapper::lessonTimeToHoLessonTimeDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createLessonTime(LessonTimeDto lessonTimeDto) {
        logger.info("In LessonTimeService create {}", lessonTimeDto);
        try {
            lessonTimeRepository.saveAndFlush(lessonTimeMapper.lessonTimeDtoToLessonTime(lessonTimeDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public LessonTimeDto getLessonTimeById(Integer id) {
        logger.info("In LessonTimeService getById {}", id);
        try {
            return lessonTimeMapper.lessonTimeToHoLessonTimeDto(lessonTimeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateLessonTime(LessonTimeDto lessonTimeDto) {
        logger.info("In LessonTimeService update {}", lessonTimeDto);
        try {
            lessonTimeRepository.saveAndFlush(lessonTimeMapper.lessonTimeDtoToLessonTime(lessonTimeDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteLessonTimeById(Integer id) {
        logger.info("In LessonTimeService delete {}", id);
        try {
            lessonTimeRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}