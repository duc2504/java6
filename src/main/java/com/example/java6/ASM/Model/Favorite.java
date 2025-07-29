package com.example.java6.ASM.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created_at ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // Người dùng thích

    @ManyToOne
    @JoinColumn(name = "sanpham_id")
    private SanPham sanPham;  // Sản phẩm được thích
}
