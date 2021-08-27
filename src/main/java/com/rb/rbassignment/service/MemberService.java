package com.rb.rbassignment.service;

import com.rb.rbassignment.data.domain.PrincipalDetails;
import com.rb.rbassignment.domain.AccessToken;
import com.rb.rbassignment.domain.Member;
import com.rb.rbassignment.domain.Role;
import com.rb.rbassignment.repository.AccessTokenRedisRepository;
import com.rb.rbassignment.repository.MemberRepository;
import com.rb.rbassignment.representative.MemberRequest;
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
    private final AccessTokenRedisRepository accessTokenRedisRepository;

    public MemberService(MemberRepository memberRepository,
                         @Lazy PasswordEncoder bCryptPasswordEncoder, AccessTokenRedisRepository accessTokenRedisRepository) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accessTokenRedisRepository = accessTokenRedisRepository;
    }

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException((email)));
        if (!ObjectUtils.isEmpty(member)) {
            return new PrincipalDetails(member);
        }
        return null;
    }

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member member = Member.builder()
            .email(memberRequest.getEmail())
            .password(bCryptPasswordEncoder.encode(memberRequest.getPassword()))
            .name(memberRequest.getName())
            .tel(memberRequest.getTel())
            .build();

        member.setRole(Role.USER);

        memberRepository.save(member);

        return MemberResponse.builder()
            .id(member.getId())
            .email(member.getEmail())
            .name(member.getName())
            .tel(member.getTel())
            .build();
    }

    @Transactional
    public void saveAccessToken(AccessToken accessToken) {
        accessTokenRedisRepository.save(accessToken);
    }

    @Transactional
    public void updateRefreshToken(String email, String refreshToken) {
        memberRepository.updateRefreshToken(email, refreshToken);
    }

    public String getAccessToken(String email) {
        return accessTokenRedisRepository.findById(email).get().getAccessToken();
    }

    public String getRefreshToken(String email) {
        return memberRepository.findByEmail(email).get().getRefreshToken();
    }
}
