package com.rb.rbassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("index");
        return mv;
    }

}
