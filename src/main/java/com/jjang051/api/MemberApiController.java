package com.jjang051.api;

import com.jjang051.entity.Member;
import com.jjang051.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberApiController {
    private final MemberService memberService;

    @PutMapping("/member/{userId}/profileImageUrl")
    public Map<String,Object> profileImageUpdate(@PathVariable String userId,
                                                 MultipartFile profileImageUrl) {

        log.info("userId==={}",userId);

        Member memberInfo =
                memberService.changeProfileImage(userId,profileImageUrl);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isUpload","ok");
        //resultMap.put("memberInfo",memberInfo)
        return resultMap;
        //img src="/upload/fjkdjksf.jpg"
    }


}
