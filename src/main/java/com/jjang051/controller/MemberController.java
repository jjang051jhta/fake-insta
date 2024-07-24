package com.jjang051.controller;

import com.jjang051.dto.SigninDto;
import com.jjang051.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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

    @PostMapping("/signin")
    public String joinProcess(@ModelAttribute SigninDto signinDto) {
        memberService.singin(signinDto); //회원가입되게
        return "member/signin";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/mypage/{id}")
    public String mypage(@PathVariable int id) {
        return "mypage"+id;
    }




}
