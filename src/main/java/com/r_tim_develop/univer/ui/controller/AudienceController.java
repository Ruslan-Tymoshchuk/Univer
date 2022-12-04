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
import com.r_tim_develop.univer.dto.AudienceDto;
import com.r_tim_develop.univer.service.AudienceService;

@Controller
@RequestMapping("/audiences")
public class AudienceController {

    private static Logger logger = LoggerFactory.getLogger(AudienceController.class);
    
    public static final String PAGE_AUDIENCES_MAIN = "redirect:/audiences/";
    public static final String PAGE_AUDIENCE_MAIN = "audience/audience_main";
    public static final String PAGE_AUDIENCE_CARD = "audience/audience_card";

    private final AudienceService audienceService;

    public AudienceController(AudienceService audienceService) {
        this.audienceService = audienceService;
    }

    @GetMapping("/")
    public String getPageWithAudiences(AudienceDto audienceDto, Model model, @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize) {
        logger.info("GET Method getPageWithAudiences was called");
        int currentPage = pageNumber.orElse(1);
        int currentSize = pageSize.orElse(5);
        Page<AudienceDto> pageWithAudiences = audienceService.findPageWithAudiences(of(currentPage - 1, currentSize));
        List<AudienceDto> listAudiences = pageWithAudiences.getContent();   
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", pageWithAudiences.getTotalPages());
        model.addAttribute("totalItems", pageWithAudiences.getTotalElements());
        model.addAttribute("listAudiences", listAudiences);
        return PAGE_AUDIENCE_MAIN;
    }

    @GetMapping("/{id}")
    public String getAudienceById(@PathVariable("id") int id, Model model) {
        logger.info("GET Method getAudienceById was called");
        model.addAttribute("audienceDto", audienceService.getAudienceById(id));
        return PAGE_AUDIENCE_CARD;
    }
 
    @PostMapping("/save")
    public String saveNewAudience(AudienceDto audienceDto) {
        logger.info("POST Method saveNewAudience was called");
        audienceService.createAudience(audienceDto);
              return PAGE_AUDIENCES_MAIN;
    }
    
    @PatchMapping("/update")
    public String updateAudience(AudienceDto audienceDto) {
        logger.info("PATCH Method updateAudience was called");
        audienceService.updateAudience(audienceDto);
              return PAGE_AUDIENCES_MAIN;
    }
       
    @DeleteMapping("/delete/{id}")
    public String deleteAudienceById(@PathVariable("id") Integer id) {
        logger.info("DELETE Method deleteAudienceById was called");
        audienceService.deleteAudienceById(id);
               return PAGE_AUDIENCES_MAIN;
    }
}