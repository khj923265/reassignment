package com.rb.rbassignment.controller;

import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.representative.MemberResponse;
import com.rb.rbassignment.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

        member.setRoleUSER(); // role USER 넣어줌

        MemberResponse memberResponse = memberService.save(member);
        mv.addObject("member", memberResponse);
        mv.setViewName("/member/login");
        return mv;
    }

    @PostMapping("/signup2")
    @ResponseBody
    public MemberResponse signup2(@RequestBody Member member) {
        return memberService.save(member);

    }

}
