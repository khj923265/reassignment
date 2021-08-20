package com.rb.rbassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ust")
public class UstController {

    @GetMapping("/shorturl")
    public String shortUrlPage() {
        return "/ust/shorturl/shorturl";
    }


    @GetMapping("/shorturl/create")
    @ResponseBody
    public String createShortUrl(@RequestParam("url") String originalUrl) {
        System.out.println("originalUrl : " + originalUrl);

        return "https://www.shortUrl.com";
    }

}
