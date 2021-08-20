package com.rb.rbassignment.domain;

import java.io.Serializable;
import java.time.LocalTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@ToString
@RedisHash("message")
public class Message implements Serializable {

    @Id
    private String id;
    private String content;
    private LocalTime sendTime;
    @Indexed // 필드 값으로 데이터 찾을 수 있게 하는 어노테이션(findByChatroomId)
    private String chatroomId;
    private String sender;

    @Builder
    public Message(String id, String content, LocalTime sendTime, String chatroomId,
        String sender) {
        this.id = id;
        this.content = content;
        this.sendTime = sendTime;
        this.chatroomId = chatroomId;
        this.sender = sender;
    }
}
