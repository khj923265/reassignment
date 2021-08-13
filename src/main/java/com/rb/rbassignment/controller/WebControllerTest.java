package com.rb.rbassignment.controller;

import com.rb.rbassignment.service.TestService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebControllerTest {

    private final TestService testService;
    private final RedisTemplate redisTemplate;

    public WebControllerTest(TestService testService, RedisTemplate redisTemplate) {
        this.testService = testService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        ModelAndView mv = new ModelAndView();

//        String result = testService.test();
//        mv.addObject("result", result);

        ValueOperations<String, String>

        mv.setViewName("index");
        return mv;
    }
}
