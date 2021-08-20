package com.rb.rbassignment.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String tel;

    public Member(){};

    @Builder
    public Member(Long id, String email, String password, String name, String tel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
    }
}