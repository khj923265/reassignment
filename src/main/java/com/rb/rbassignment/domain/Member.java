package com.rb.rbassignment.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String tel;
    @Column
    private String refreshToken;
    @Column
    private String membershipRank;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public Member(){};

    @Builder
    public Member(Long id, String email, String password, String name, String tel, String refreshToken, String membershipRank) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.refreshToken = refreshToken;
        this.membershipRank = membershipRank;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}