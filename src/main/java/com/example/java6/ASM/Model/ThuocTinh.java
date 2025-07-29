package com.example.java6.ASM.Model;



import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ThuocTinh")
public class ThuocTinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maThuocTinh;

    @ManyToOne
    @JoinColumn(name = "maSKU")
    private BienTheSanPham bienTheSanPham;

    @Column(length = 100)
    private String tenThuocTinh;

    @Column(length = 100)
    private String tenThuocTinhBienThe;

    private Integer trangThai;
}
