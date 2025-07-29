package com.example.java6.ASM.DTO;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamFullDTO {
//    san pham
    private Integer maSanPham;
    private String tenSanPham;
    private Integer soLuongSanPham;
    private BigDecimal giaSanPham;
////    bien the
//    private String maSKU;
//    private BigDecimal giaBienThe;
//    private Integer soLuongBienThe;
//    private Integer trangThai;
////    thuoc tinh
////    private String tenThuocTinh;
////    private String tenThuocTinhBienThe;
//
//    private String thuocTinh ;
private List<BienTheSanPhamDTO> bienTheSanPham;
}
