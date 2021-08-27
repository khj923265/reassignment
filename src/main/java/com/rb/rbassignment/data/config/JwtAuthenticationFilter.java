package com.rb.rbassignment.data.config;

import com.rb.rbassignment.domain.AccessToken;
import com.rb.rbassignment.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 인증에 성공하면 spring 이 관리하는 SecurityContext 에 인증객체를 설정해준다!
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, MemberService memberService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 쿠키에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);

        // 유효한 토큰인지 확인합니다.
        if (StringUtils.isNotBlank(accessToken)) {
            String email = jwtTokenProvider.getUsernameByCookie((HttpServletRequest) request);

            if (jwtTokenProvider.validateToken(accessToken) &&
                        accessToken.equals(memberService.getAccessToken(email))) {
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(accessToken));
            } else if (!jwtTokenProvider.validateToken(accessToken)){
                // 토큰은 있지만 유요하지 않으면 DB 에서 refreshToken 조회해서 재발급
                String refreshToken = memberService.getRefreshToken(email);
                if (jwtTokenProvider.validateToken(refreshToken)) {
                    String reAccessToken = jwtTokenProvider.createAccessToken(email, "USER");
                    memberService.saveAccessToken(AccessToken.builder()
                            .email(email)
                            .accessToken(reAccessToken)
                            .build());
                    SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(reAccessToken));
                } else {
                    // accessToken, refreshToken 둘다 만료

                }
            }
        }

        chain.doFilter(request, response);
    }
}