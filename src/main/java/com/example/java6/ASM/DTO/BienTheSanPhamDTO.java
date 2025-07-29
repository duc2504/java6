package com.example.java6.ASM.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BienTheSanPhamDTO {
    private String maSKU;
    private BigDecimal giaBienThe;
    private Integer soLuongBienThe;
    private Integer trangThai;
    private String thuocTinh ;
}
