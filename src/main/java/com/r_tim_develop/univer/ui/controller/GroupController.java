package com.r_tim_develop.univer.ui.controller;

import static org.springframework.data.domain.PageRequest.of;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.r_tim_develop.univer.dto.GroupDto;
import com.r_tim_develop.univer.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private static Logger logger = LoggerFactory.getLogger(GroupController.class);

    public static final String PAGE_GROUP_MAIN = "group/group_main";
    public static final String PAGE_GROUP_CARD = "group/group_card";
    public static final String PAGE_GROUPS_REDIRECT = "redirect:/groups/";

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public String getPageWithGroups(GroupDto groupDto, Model model, @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {
        logger.info("GET Method getPageWithGroups was called");
        int currentPage = pageNumber.orElse(1);
        int currentSize = pageSize.orElse(5);
        Page<GroupDto> pageWithGroups = groupService.findPageWithGroups(of(currentPage - 1, currentSize));
        List<GroupDto> listGroups = pageWithGroups.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageWithGroups.getTotalPages());
        model.addAttribute("totalItems", pageWithGroups.getTotalElements());
        model.addAttribute("listGroups", listGroups);
        return PAGE_GROUP_MAIN;
    }

    @GetMapping("/{id}")
    public String getGroupById(@PathVariable("id") Integer id, Model model) {
        logger.info("GET Method getGroupById was called");
        model.addAttribute("groupDto", groupService.getGroupById(id));
        return PAGE_GROUP_CARD;
    }

    @PostMapping("/save")
    public String saveNewGroup(GroupDto groupDto) {
        logger.info("POST Method saveNewGroup was called");
        groupService.createGroup(groupDto);
        return PAGE_GROUPS_REDIRECT;
    }

    @PatchMapping("/update")
    public String updateGroup(GroupDto groupDto) {
        logger.info("PATCH Method updateGroup was called");
        groupService.updateGroup(groupDto);
        return PAGE_GROUPS_REDIRECT;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGroupById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteGroupById was called");
        groupService.deleteGroupById(id);
        return PAGE_GROUPS_REDIRECT;
    }
}