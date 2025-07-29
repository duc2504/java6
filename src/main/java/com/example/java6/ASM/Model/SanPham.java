package com.example.java6.ASM.Model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maSanPham;

    @Column(length = 255)
    private String tenSanPham;

    private Integer soLuong;

    private BigDecimal gia;

    private Integer trangThai;

    @OneToMany(mappedBy = "sanPham")
    private List<Favorite> favoriteList;



    @OneToMany(mappedBy = "sanPham")
    private List<FeedBack> feedBackList;
}
