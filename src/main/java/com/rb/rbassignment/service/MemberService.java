package com.rb.rbassignment.service;

import com.rb.rbassignment.data.domain.PrincipalDetails;
import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.repository.MemberRepository;
import com.rb.rbassignment.representative.MemberResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository,@Lazy PasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
        if(!ObjectUtils.isEmpty(member)){
            return new PrincipalDetails(member);
        }
        return null;
    }

    public MemberResponse save(Member member) {
        memberRepository.save(member);

        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .tel(member.getTel())
                .build();

    }
}
