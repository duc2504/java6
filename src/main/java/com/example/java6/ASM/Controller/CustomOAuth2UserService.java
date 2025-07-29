package com.example.java6.ASM.Controller;

import com.example.java6.ASM.Model.Role;
import com.example.java6.ASM.Model.User;
import com.example.java6.ASM.Repository.RoleRepository;
import com.example.java6.ASM.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // Tìm user trong DB theo email
        User user = userRepository.findByUserName(email).orElse(null);

        if (user == null) {
            // Tìm Role mặc định là USER
            Role defaultRole = roleRepository.findByRoleName("USER");
            if (defaultRole == null) {
                defaultRole = new Role();
                defaultRole.setRoleName("USER");
                defaultRole = roleRepository.save(defaultRole);
            }

            // Tạo user mới
            user = new User();
            user.setUserName(email);
            user.setPass(""); // hoặc null nếu không cần
            user.setRole(defaultRole);

            userRepository.save(user);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleName())),
                oauth2User.getAttributes(),
                "email"
        );
    }
}
