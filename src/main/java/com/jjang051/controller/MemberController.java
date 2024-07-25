package com.jjang051.controller;

import com.jjang051.dto.CustomUserDetails;
import com.jjang051.dto.MemberDto;
import com.jjang051.dto.SigninDto;
import com.jjang051.entity.Member;
import com.jjang051.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signin")
    public String join(Model model) {
        model.addAttribute("signinDto",new SigninDto());
        return "member/signin";
    }

    @GetMapping("/mypage/{userId}")
    public String mypage(Model model,@PathVariable String userId) {
        //id를 가지고 찾은 정보를 넘겨줘야함...
        MemberDto memberInfoDto = memberService.getInfoMember(userId);
        //repository에서 찾아서 dto로 바꿔서 front전달
        model.addAttribute("memberInfoDto",memberInfoDto);
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
