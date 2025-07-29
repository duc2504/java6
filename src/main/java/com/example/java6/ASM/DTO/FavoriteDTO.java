package com.example.java6.ASM.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {
    private Long id;
    private Integer sanPhamId;
    private String tenSanPham;
    private LocalDateTime createdAt;
}
