package com.r_tim_develop.univer.service.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.r_tim_develop.univer.dto.LessonDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.LessonRepository;
import com.r_tim_develop.univer.service.LessonService;

@Service
@Transactional(readOnly = true)
public class LessonServiceImpl implements LessonService {

    public static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;
    private final MapStructMapper lessonMapper;

    public LessonServiceImpl(LessonRepository lessonRepository, MapStructMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<LessonDto> findAllLessons() {
        logger.info("In LessonService findAll");
        try {
            return lessonRepository.findAll().stream().map(lessonMapper::lessonToLessonDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createLesson(LessonDto lessonDto) {
        logger.info("In LessonService create {}", lessonDto);
        try {
            lessonRepository.saveAndFlush(lessonMapper.lessonDtoToLesson(lessonDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public LessonDto getLessonById(Integer id) {
        logger.info("In LessonService getById {}", id);
        try {
            return lessonMapper.lessonToLessonDto(lessonRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateLesson(LessonDto lessonDto) {
        logger.info("In LessonService update {}", lessonDto);
        try {
            lessonRepository.saveAndFlush(lessonMapper.lessonDtoToLesson(lessonDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteLessonById(Integer id) {
        logger.info("In LessonService delete {}", id);
        try {
            lessonRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<LessonDto> findByGroupIdAndDateInterval(Integer id, LocalDate startDate, LocalDate endDate) {
        logger.info("In LessonService findByGroupIdAndDateInterval {}, {}, {}", id, startDate, endDate);
        try {
            return lessonRepository.findByGroupIdAndDateInterval(id, startDate, endDate).stream()
                    .map(lessonMapper::lessonToLessonDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<LessonDto> findByTeacherAndDateInterval(Integer id, LocalDate startDate, LocalDate endDate) {
        logger.info("In LessonService findByTeacherAndDateInterval {}, {}, {}", id, startDate, endDate);
        try {
            return lessonRepository.findByTeacherAndDateInterval(id, startDate, endDate).stream()
                    .map(lessonMapper::lessonToLessonDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}