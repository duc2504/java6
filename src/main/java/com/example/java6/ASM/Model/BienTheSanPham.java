package com.example.java6.ASM.Model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BienTheSanPham")
public class BienTheSanPham {
    @Id
    @Column(length = 50)
    private String maSKU;

    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SanPham sanPham;

    private BigDecimal gia;

    private Integer soLuong;

    private Integer trangThai;

    @OneToMany(mappedBy = "bienTheSanPham")
    private List<ThuocTinh> thuocTinhList;
}

