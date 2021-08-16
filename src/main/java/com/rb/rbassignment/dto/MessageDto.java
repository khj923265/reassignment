package com.rb.rbassignment.dto;

import lombok.Builder;

public class MessageDto {
    private String id;
    private String message;
    private String send_time;
    private String chatroom_id;
    private String sender;

    @Builder
    public MessageDto(String id, String message, String send_time, String chatroom_id,
        String sender) {
        this.id = id;
        this.message = message;
        this.send_time = send_time;
        this.chatroom_id = chatroom_id;
        this.sender = sender;
    }
}
