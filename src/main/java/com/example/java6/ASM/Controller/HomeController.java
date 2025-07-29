package com.example.java6.ASM.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "sanpham/login"; // trỏ tới login.html
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "sanpham/403";
    }




}
