package com.rb.rbassignment.controller;

import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
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
    public ModelAndView signup(@RequestBody Member member) {
        ModelAndView mv = new ModelAndView();
        int result = memberService.signup(member);
        if (result == 1) {
            mv.addObject("result", "성공");
        } else {
            mv.addObject("result", "실패");
        }

        mv.setViewName("/member/login");
        return mv;
    }

    @PostMapping("/restsignup")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> restSignup(@RequestBody Member member) {
        int result = memberService.signup(member);
        Map<String, Object> map = new HashMap<>();

        if (result == 1) {
            map.put("result", "성공");
        } else {
            map.put("result", "실패");
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
