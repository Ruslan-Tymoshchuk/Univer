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
import com.r_tim_develop.univer.dto.CourseDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.CourseRepository;
import com.r_tim_develop.univer.service.CourseService;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl implements CourseService {

    public static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final MapStructMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, MapStructMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public Page<CourseDto> findPageWithCourses(Pageable pageable) {
        logger.info("In CourseService findPageWithCourses");
        try {
            return courseRepository.findAll(pageable).map(courseMapper::courseToCourseDto);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<CourseDto> findAllCourses() {
        logger.info("In CourseService findAll");
        try {
            return courseRepository.findAll().stream().map(courseMapper::courseToCourseDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createCourse(CourseDto courseDto) {
        logger.info("In CourseService create {}", courseDto);
        try {
            courseRepository.saveAndFlush(courseMapper.courseDtoToCourse(courseDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public CourseDto getCourseById(Integer id) {
        logger.info("In CourseService getById {}", id);
        try {
            return courseMapper.courseToCourseDto(courseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateCourse(CourseDto courseDto) {
        logger.info("In CourseService update {}", courseDto);
        try {
            courseRepository.saveAndFlush(courseMapper.courseDtoToCourse(courseDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteCourseById(Integer id) {
        logger.info("In CourseService delete {}", id);
        try {
            courseRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}