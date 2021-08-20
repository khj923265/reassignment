package com.rb.rbassignment.service;

import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public int signup(Member member) {
        return memberRepository.save(member);
    }
}
