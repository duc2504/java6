package com.example.java6.ASM.config;

import com.example.java6.ASM.DTO.VerifyResult;
import com.example.java6.ASM.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify")
    public ResponseEntity<Object> verify(@RequestBody String token) {
        String username = jwtUtil.extractUsername(token);
        boolean isValid = jwtUtil.validateToken(token, username);
        VerifyResult result = new VerifyResult();
        result.setUsername(username);
        result.setValid(isValid);
        return ResponseEntity.ok(result);
    }
}
