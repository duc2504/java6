package com.example.java6.ASM.DTO;



import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamDTO {
    private Integer maSanPham;
    private String tenSanPham;
    private Integer soLuong;
    private BigDecimal gia;
    private Integer trangThai;
}
