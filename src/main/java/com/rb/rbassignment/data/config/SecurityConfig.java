package com.rb.rbassignment.data.config;

import com.rb.rbassignment.controller.securityhandler.CustomAccessDeniedHandler;
import com.rb.rbassignment.controller.securityhandler.CustomAuthFailureHandler;
import com.rb.rbassignment.controller.securityhandler.LoginSuccessHandler;
import com.rb.rbassignment.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    // 패스워드 인코딩을 사용하기 위한 빈
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // audit을 사용 할 때 유저의 이름을 가져올 수 있게하는 빈
    @Bean
    public AuditorAware<String> defaultAuditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return Optional.of("unknown");
            }
            return Optional.ofNullable(authentication.getName());
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/assets/**", "/static/**");
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();


        http.cors().and().csrf().disable().formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션사용 X
                .and()
                .authorizeRequests() // 페이지 권한 설정
                .antMatchers("/ust/**").hasRole("USER")
                .anyRequest().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/member/login")
                .usernameParameter("email")
//                .defaultSuccessUrl("/member/loginsuccess")
                .successHandler(new LoginSuccessHandler(jwtTokenProvider, memberService))
                .failureHandler(new CustomAuthFailureHandler())
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/member/login")
                .deleteCookies("X-AUTH-TOKEN")
                .deleteCookies("USER")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, memberService),
                        UsernamePasswordAuthenticationFilter.class);
        // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
