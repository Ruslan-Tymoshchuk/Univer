package com.r_tim_develop.univer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.r_tim_develop.univer.dto.GroupDto;

public interface GroupService {

    Page<GroupDto> findPageWithGroups(Pageable pageable);
    
    List<GroupDto> findAllGroups();

    void createGroup(GroupDto groupDto);

    GroupDto getGroupById(Integer id);

    void updateGroup(GroupDto groupDto);

    void deleteGroupById(Integer id);
   
}