package com.jjang051.controller;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.dto.MemberDto;
import com.jjang051.dto.SigninDto;
import com.jjang051.entity.Member;
import com.jjang051.entity.Story;
import com.jjang051.service.MemberService;
import com.jjang051.service.StoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final StoryService storyService;

    @GetMapping("/signin")
    public String join(Model model) {
        model.addAttribute("signinDto",new SigninDto());
        return "member/signin";
    }

    @GetMapping("/mypage/{userId}")
    public String mypage(Model model,@PathVariable String userId,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //id를 가지고 찾은 정보를 넘겨줘야함...
        //로그인한 사용자만 보여주기...
//        List<Story> storyList =
//        storyService.loadStory(customUserDetails.getLoggedMember().getId());

        //로그인한 사용자만 보여주기...
        List<Story> storyList =
                storyService.loadStory(userId);

        MemberDto memberInfoDto = memberService.getInfoMember(userId);
        //repository에서 찾아서 dto로 바꿔서 front전달
        model.addAttribute("memberInfoDto",memberInfoDto);
        model.addAttribute("storyList",storyList);
        return "member/mypage";
    }

    @PostMapping("/signin")
    public String joinProcess(@ModelAttribute SigninDto signinDto) {
        memberService.singin(signinDto); //회원가입되게
        return "redirect:/member/login";
    }


    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/modify")
        public String modify(MemberDto memberDto, String userId,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member member =  memberService.modifyMember(memberDto, customUserDetails.getLoggedMember().getUserId());
        customUserDetails.setLoggedMember(member);
        //내용바로 바뀌게...
        return "redirect:/";
    }




}
