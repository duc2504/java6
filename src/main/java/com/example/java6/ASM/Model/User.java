package com.example.java6.ASM.Model;



import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String userName;

    @Column(length = 50)
    private String pass;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<FeedBack> feedBackList;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favoriteList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về role dạng GrantedAuthority
        return Collections.singleton(() -> "ROLE_" + role.getRoleName());
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    // Các phương thức còn lại (mặc định true)
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

