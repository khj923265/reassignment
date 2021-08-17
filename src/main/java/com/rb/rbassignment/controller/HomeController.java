package com.rb.rbassignment.controller;

import com.rb.rbassignment.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final TestService testService;

    public HomeController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView();
        String result = testService.getTest();

        mv.addObject("result", result);
        mv.setViewName("index");
        return mv;
    }

}
