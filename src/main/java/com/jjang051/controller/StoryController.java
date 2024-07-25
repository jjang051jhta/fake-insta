package com.jjang051.controller;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.dto.StoryUploadDto;
import com.jjang051.service.StoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/story")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    @GetMapping("/upload")
    public String upload() {
        return "story/upload";
    }


    @PostMapping("/upload")
    public String uploadProcess(StoryUploadDto storyUploadDto,
                                @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        storyService.upload(storyUploadDto,customUserDetails);
        return "redirect:/";
    }

}
