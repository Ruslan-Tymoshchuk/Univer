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
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.dto.VacationDto;
import com.r_tim_develop.univer.model.Vacation;
import com.r_tim_develop.univer.repository.VacationRepository;
import com.r_tim_develop.univer.service.VacationService;

@Service
@Transactional(readOnly = true)
public class VacationServiceImpl implements VacationService {

    public static final Logger logger = LoggerFactory.getLogger(VacationServiceImpl.class);

    private final VacationRepository vacationRepository;
    private final MapStructMapper vacationMapper;

    public VacationServiceImpl(VacationRepository vacationRepository, MapStructMapper vacationMapper) {
        this.vacationRepository = vacationRepository;
        this.vacationMapper = vacationMapper;
    }

    @Override
    public List<VacationDto> findAllVacations() {
        logger.info("In VacationService findAll");
        try {
            return vacationRepository.findAll().stream().map(vacationMapper::vacationToVacationDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createVacation(VacationDto vacationDto) {
        logger.info("In VacationService create {}", vacationDto);
        try {
            vacationRepository.saveAndFlush(vacationMapper.vacationDtoToVacation(vacationDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public VacationDto getVacationById(Integer id) {
        logger.info("In VacationService getById {}", id);
        try {
            return vacationMapper.vacationToVacationDto(vacationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateVacation(VacationDto vacationDto) {
        logger.info("In VacationService update {}", vacationDto);
        try {
            vacationRepository.saveAndFlush(vacationMapper.vacationDtoToVacation(vacationDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteVacationById(Integer id) {
        logger.info("In VacationService delete {}", id);
        try {
            vacationRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<Vacation> findVacationsByDateInterval(LocalDate startDate, LocalDate endDate) {
        logger.info("In VacationService findByDateInterval {}, {}", startDate, endDate);
        try {
            return vacationRepository.findByDateInterval(startDate, endDate);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}