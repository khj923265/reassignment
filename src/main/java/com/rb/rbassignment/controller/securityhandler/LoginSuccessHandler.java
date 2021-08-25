//package com.rb.rbassignment.controller.securityhandler;
//
//import com.rb.rbassignment.data.config.JwtTokenProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class LoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public LoginSuccessHandler(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//        Authentication authentication) throws IOException, ServletException {
////        String email = request.getParameter("email");
////        String result = jwtTokenProvider.createToken(email, Role.USER);
//        //TODO 시큐리티 + JWT
//        //roleNames.contains << 로그인한 유저의 role
////        List<String> roleNames = new ArrayList<>();
////        authentication.getAuthorities().forEach(authority ->{roleNames
////            .add(authority.getAuthority());
////        });
//
//        response.sendRedirect("http://localhost:8080");
//    }
//}
