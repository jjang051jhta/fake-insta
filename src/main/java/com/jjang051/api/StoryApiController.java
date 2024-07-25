package com.jjang051.api;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.entity.Story;
import com.jjang051.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoryApiController {
    private final StoryService storyService;

    @GetMapping("/story")
    public ResponseEntity<Map<String,Object>> loadStory(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ) {
        //내가 올린 스토리를 들고 온다.
        List<Story> storyList =
                storyService.loadStory(customUserDetails.getLoggedMember().getId());
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("storyList",storyList);
        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
        // 내가 쓴 것만 출력되게....
    }
}
