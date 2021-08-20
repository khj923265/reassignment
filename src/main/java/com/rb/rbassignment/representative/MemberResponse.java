package com.rb.rbassignment.representative;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private String tel;

    public MemberResponse() {};

    @Builder
    public MemberResponse(Long id, String email, String name, String tel) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.tel = tel;
    }
}
