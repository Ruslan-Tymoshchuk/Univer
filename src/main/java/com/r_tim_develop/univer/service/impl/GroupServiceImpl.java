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
import com.r_tim_develop.univer.dto.GroupDto;
import com.r_tim_develop.univer.dto.MapStructMapper;
import com.r_tim_develop.univer.repository.GroupRepository;
import com.r_tim_develop.univer.service.GroupService;

@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {

    public static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupRepository groupRepository;
    private final MapStructMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, MapStructMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public Page<GroupDto> findPageWithGroups(Pageable pageable) {
        logger.info("In CourseService findPageWithCourses");
        try {
            return groupRepository.findAll(pageable).map(groupMapper::groupToGroupDto);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    public List<GroupDto> findAllGroups() {
        logger.info("In GroupService findAll");
        try {
            return groupRepository.findAll().stream().map(groupMapper::groupToGroupDto).toList();
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void createGroup(GroupDto groupDto) {
        logger.info("In GroupService create {}", groupDto);
        try {
            groupRepository.saveAndFlush(groupMapper.groupDtoToGroup(groupDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);

        }
    }

    @Override
    public GroupDto getGroupById(Integer id) {
        logger.info("In GroupService getById {}", id);
        try {
            return groupMapper.groupToGroupDto(groupRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + id + " id")));
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void updateGroup(GroupDto groupDto) {
        logger.info("In GroupService update {}", groupDto);
        try {
            groupRepository.saveAndFlush(groupMapper.groupDtoToGroup(groupDto));
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }

    @Override
    @Transactional
    public void deleteGroupById(Integer id) {
        logger.info("In GroupService delete {}", id);
        try {
            groupRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new ServiceException("Repository error occured ", e);
        }
    }
}