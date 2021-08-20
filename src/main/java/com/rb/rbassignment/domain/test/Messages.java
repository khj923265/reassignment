package com.rb.rbassignment.domain.test;

import lombok.Data;

@Data
public class Messages {
    private String messageStatusName;
    private String messageId;
    private String completeTime;
    private String requestStatusDesc;
    private String content;
    private String requestTime;
    private String requestStatusName;
    private String requestStatusCode;
    private String messageStatusDesc;
    private String countryCode;
    private String messageStatusCode;
    private String plusFriendId;
    private String to;
    private boolean useSmsFailover;
    private Failover failover;

    @Data
    public static class Failover {
        private String smsServiceId;
        private String requestId;
        private String requestStatusCode;
        private String requestStatusName;
        private String requestStatusDesc;
    }

}
