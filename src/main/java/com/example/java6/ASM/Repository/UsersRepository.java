package com.example.java6.ASM.Repository;


import com.example.java6.ASM.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
