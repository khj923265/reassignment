package com.rb.rbassignment.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@ToString
@RedisHash("message")
public class Message implements Serializable {

    @Id
    private String id;
    private String message;
    private String send_time;
    private String chatroom_id;
    private String sender;

    @Builder
    public Message(String id, String message, String send_time, String chatroom_id,
        String sender) {
        this.id = id;
        this.message = message;
        this.send_time = send_time;
        this.chatroom_id = chatroom_id;
        this.sender = sender;
    }
}
