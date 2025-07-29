package com.example.java6.ASM.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            // Đăng nhập bằng tài khoản thường
            session.setAttribute("currentUsername", userDetails.getUsername());
        } else if (principal instanceof OAuth2User oauth2User) {
            // Đăng nhập bằng Google
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            session.setAttribute("currentEmail", email);
            session.setAttribute("currentName", name);
        }

        response.sendRedirect("/san-pham"); // hoặc redirect nơi bạn muốn
    }
}
