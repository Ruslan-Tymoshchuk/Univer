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
import com.r_tim_develop.univer.dto.StudentDto;
import com.r_tim_develop.univer.repository.StudentRepository;
import com.r_tim_develop.univer.service.StudentService;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    public static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final MapStructMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, MapStructMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Page<StudentDto> findPageWithStudents(Pageable pageable) {
        logger.info("In CourseService findPageWithCourses");
        try {
            return studentRepository.findAll(pageable).map(studentMapper::studentToStudentDto);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<StudentDto> findAllStudents() {
        logger.info("In StudentService findAll");
        try {
            return studentRepository.findAll().stream().map(studentMapper::studentToStudentDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createStudent(StudentDto studentdto) {
        logger.info("In StudentService create {}", studentdto);
        try {
            studentRepository.saveAndFlush(studentMapper.studentDtoToStudent(studentdto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public StudentDto getStudentById(int id) {
        logger.info("In StudentService getById {}", id);
        try {
            return studentMapper.studentToStudentDto(studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateStudent(StudentDto studentdto) {
        logger.info("In StudentService update {}", studentdto);
        try {
            studentRepository.saveAndFlush(studentMapper.studentDtoToStudent(studentdto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteStudentById(Integer id) {
        logger.info("In StudentService delete {}", id);
        try {
            studentRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}