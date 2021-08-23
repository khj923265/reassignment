package com.rb.rbassignment.representative;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String tel;

}
