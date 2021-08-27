package com.rb.rbassignment.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@ToString
@RedisHash("accesstoken")
public class AccessToken implements Serializable {

    @Id
    private String email;
    private String accessToken;

    public AccessToken(){}

    @Builder
    public AccessToken(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

}
