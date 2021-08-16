package com.rb.rbassignment.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@ToString
@RedisHash("chatRoom")
public class ChatRoom implements Serializable {

    @Id
    private String id;
    private String title;

    @Builder
    public ChatRoom(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
