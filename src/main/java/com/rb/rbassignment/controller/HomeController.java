package com.rb.rbassignment.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView homePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("USER".equals(cookie.getName())) {
                    mv.addObject("username", cookie.getValue());
                }
            }
        }

        mv.setViewName("index");
        return mv;
    }

}
