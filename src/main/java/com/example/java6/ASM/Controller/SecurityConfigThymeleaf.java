package com.example.java6.ASM.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@Order(2)
public class SecurityConfigThymeleaf {

//    @Bean
//    public SecurityFilterChain thymeleafSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//
//                .securityMatcher("/**") // áp dụng cho các request giao diện
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/css/**", "/js/**", "/img/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/san-pham", true)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                )
//                .exceptionHandling(ex -> ex
//                        .accessDeniedPage("/403") // Trang bị từ chối
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // ❗ Thêm dòng này để tắt CSRF
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/login", "/oauth2/**", "/css/**", "/js/**").permitAll()
//                        .requestMatchers("/dichvu/**").authenticated()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth -> oauth
//                        .loginPage("/login") // Tùy biến trang login
//                        .defaultSuccessUrl("/san-pham", true)
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // URL để xử lý logout
//                        .logoutSuccessUrl("/login?logout") // Sau khi logout sẽ chuyển đến đây
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
@Autowired
private CustomLoginSuccessHandler loginSuccessHandler;
    @Bean
    public SecurityFilterChain  securityFilterChainThymeleaf(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/oauth2/**", "/css/**", "/js/**", "/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/san-pham", true)
                        .successHandler(loginSuccessHandler) // Gắn custom handler
                        .permitAll()
                )
                .oauth2Login(oauth -> oauth
                                .loginPage("/login")
                                .defaultSuccessUrl("/san-pham", true)
                                .successHandler(loginSuccessHandler)
                        // .userInfoEndpoint(...) nếu cần
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/403")
                );

        return http.build();
    }
}
