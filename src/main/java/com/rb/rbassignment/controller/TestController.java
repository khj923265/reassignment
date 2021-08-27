package com.rb.rbassignment.controller;

import com.rb.rbassignment.domain.test.Messages;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/message")
    public List<Messages> messageTest(@RequestBody List<Messages> messages) {
        long totalCount = messages.size();
        long failoverCount = 0;

        long successCount = messages.stream()
                .filter(message -> "0000".equals(message.getMessageStatusCode()))
                .count();

        if (messages.get(0).isUseSmsFailover()) {
            failoverCount = messages.stream()
                    .filter(message -> !"202".equals(message.getFailover().getRequestStatusCode()))
                    .count();
        }

        long failCount = totalCount - successCount;

        System.out.println("totalCount : " + totalCount);
        System.out.println("successCount : " + successCount);
        System.out.println("failCount : " + failCount);
        if (failoverCount != 0) {
            System.out.println("failoverSuccessCount : " + failoverCount);
            System.out.println("failoverFailCount : " + (failCount - failoverCount));
        } else {
            System.out.println("failover 사용안함");
        }


        return messages;
    }

}
