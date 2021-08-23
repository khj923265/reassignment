package com.rb.rbassignment.controller;

import com.rb.rbassignment.data.config.JwtTokenProvider;
import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.repository.MemberRepository;
import com.rb.rbassignment.representative.MemberRequest;
import com.rb.rbassignment.representative.MemberResponse;
import com.rb.rbassignment.service.MemberService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public MemberController(MemberService memberService,
        MemberRepository memberRepository,
        JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("login")
    public String loginPage() {
        return "/member/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "/member/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signup(MemberRequest member) {
        ModelAndView mv = new ModelAndView();

        MemberResponse memberResponse = memberService.save(member);
        mv.addObject("member", memberResponse);
        mv.setViewName("/member/login");
        return mv;
    }

}
