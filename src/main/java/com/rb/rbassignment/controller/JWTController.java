package com.rb.rbassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jwt")
public class JWTController {

    @GetMapping("")
    @ResponseBody
    public String createJwt() {
        return "";
    }
}
