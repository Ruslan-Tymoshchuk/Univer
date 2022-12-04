package com.r_tim_develop.univer.service.impl;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.r_tim_develop.univer.dto.HolidayDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.HolidayRepository;
import com.r_tim_develop.univer.service.HolidayService;

@Service
@Transactional(readOnly = true)
public class HolidayServiceImpl implements HolidayService {

    public static final Logger logger = LoggerFactory.getLogger(HolidayServiceImpl.class);

    private final HolidayRepository holidayRepository;
    private final MapStructMapper holidayMapper;

    public HolidayServiceImpl(HolidayRepository holidayRepository, MapStructMapper holidayMapper) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
    }

    @Override
    public List<HolidayDto> findAllHolidays() {
        logger.info("In HolidayService findAll");
        try {
            return holidayRepository.findAll().stream().map(holidayMapper::holidayToHolidayDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createHoliday(HolidayDto holidayDto) {
        logger.info("In HolidayService create {}", holidayDto);
        try {
            holidayRepository.saveAndFlush(holidayMapper.holidayDtoToHoliday(holidayDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public HolidayDto getHolidayById(Integer id) {
        logger.info("In HolidayService getById {}", id);
        try {
            return holidayMapper.holidayToHolidayDto(holidayRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public HolidayDto findHolidayByName(String name) {
        logger.info("In HolidayService findHolidayByName {}", name);
        try {
            return holidayMapper.holidayToHolidayDto(holidayRepository.findByName(name));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateHoliday(HolidayDto holidayDto) {
        logger.info("In HolidayService update {}", holidayDto);
        try {
            holidayRepository.saveAndFlush(holidayMapper.holidayDtoToHoliday(holidayDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteHolidayById(Integer id) {
        logger.info("In HolidayService delete {}", id);
        try {
            holidayRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}