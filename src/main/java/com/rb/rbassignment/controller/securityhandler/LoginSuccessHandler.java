package com.rb.rbassignment.controller.securityhandler;

import com.rb.rbassignment.data.config.JwtTokenProvider;
import com.rb.rbassignment.domain.AccessToken;
import com.rb.rbassignment.domain.Role;
import com.rb.rbassignment.service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    public LoginSuccessHandler(JwtTokenProvider jwtTokenProvider, MemberService memberService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String email = request.getParameter("email");

        String accessToken = jwtTokenProvider.createAccessToken(email, Role.USER.getSymbol());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        memberService.saveAccessToken(AccessToken.builder()
                .email(email)
                .accessToken(accessToken)
                .build());

        memberService.updateRefreshTokenOnQueryDsl(email, refreshToken);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", accessToken);
        cookie.setMaxAge(60 * 60); // 1시간
        cookie.setPath("/"); // 쿠키접근 권한 주소설정?
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("USER", email);
        cookie2.setMaxAge(60 * 60);
        cookie2.setPath("/");
        response.addCookie(cookie2);

        response.sendRedirect("http://localhost:8080");
    }
}
