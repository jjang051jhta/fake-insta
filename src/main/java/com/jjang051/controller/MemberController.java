package com.jjang051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/signin")
    //@ResponseBody
    public String join(Model model) {
        return "member/signin";
    }

    @GetMapping("/login")
    @ResponseBody
    public String login() {
        return "login";
    }

    @GetMapping("/mypage/{id}")
    @ResponseBody
    public String mypage(@PathVariable int id) {
        return "mypage"+id;
    }




}
