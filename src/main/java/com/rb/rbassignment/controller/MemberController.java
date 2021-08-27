package com.rb.rbassignment.controller;

import com.rb.rbassignment.representative.MemberRequest;
import com.rb.rbassignment.representative.MemberResponse;
import com.rb.rbassignment.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signup(MemberRequest member) {
        ModelAndView mv = new ModelAndView();

        MemberResponse memberResponse = memberService.save(member);
        mv.addObject("member", memberResponse);
        mv.setViewName("member/login");
        return mv;
    }

    @GetMapping("/loginsuccess")
    public String loginSuccess() {
        return "index";
    }

}
