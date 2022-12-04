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
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.dto.TeacherDto;
import com.r_tim_develop.univer.repository.TeacherRepository;
import com.r_tim_develop.univer.service.TeacherService;

@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    public static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;
    private final MapStructMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, MapStructMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Page<TeacherDto> findPageWithTeachers(Pageable pageable) {
        logger.info("In CourseService findPageWithTeachers");
        try {
            return teacherRepository.findAll(pageable).map(teacherMapper::teacherToTeacherDto);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<TeacherDto> findAllTeachers() {
        logger.info("In TeacherService findAll");
        try {
            return teacherRepository.findAll().stream().map(teacherMapper::teacherToTeacherDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createTeacher(TeacherDto teacherDto) {
        logger.info("In TeacherService create {}", teacherDto);
        try {
            teacherRepository.saveAndFlush(teacherMapper.teacherDtoToTeacher(teacherDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public TeacherDto getTeacherById(int id) {
        logger.info("In TeacherService getById {}", id);
        try {
            return teacherMapper.teacherToTeacherDto(teacherRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateTeacher(TeacherDto teacherdto) {
        logger.info("In TeacherService update {}", teacherdto);
        try {
            teacherRepository.saveAndFlush(teacherMapper.teacherDtoToTeacher(teacherdto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteTeacherById(Integer id) {
        logger.info("In TeacherService delete {}", id);
        try {
            teacherRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}