package com.jjang051.api;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscribeApiController {
    private final SubscribeService subscribeService;
    @PostMapping("/subscribe/{toMemberId}")
    public ResponseEntity<Map<String,Object>> subscribe(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int toMemberId
    ) {
        //로그인한 사람이 다른 사람 구독 누른다.
        subscribeService.subscribe(
                customUserDetails.getLoggedMember().getId(),
                toMemberId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isSubscribe","ok");
        return ResponseEntity.status(HttpStatus.OK).body(resultMap);
    }
}
