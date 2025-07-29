package com.example.java6.ASM.Repository;



import com.example.java6.ASM.DTO.SanPhamFullDTO;
import com.example.java6.ASM.Model.SanPham;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
//    tt.tenThuocTinh AS tenThuocTinh,
//    tt.tenThuocTinhBienThe AS tenThuocTinhBienThe
//    @Query(value = """
//    SELECT
//
//         sp.maSanPham AS maSanPham,
//         sp.tenSanPham AS tenSanPham,
//         sp.soLuong AS soLuongSanPham,
//         sp.gia AS giaSanPham,
//
//         bt.maSKU AS maSKU,
//         bt.gia AS giaBienThe,
//         bt.soLuong AS soLuongBienThe,
//         bt.trangThai AS trangThai,
//
//         STRING_AGG(tt.tenThuocTinh + ': ' + tt.tenThuocTinhBienThe, ', ') AS thuocTinh
//
//     FROM SanPham sp
//     JOIN BienTheSanPham bt ON sp.maSanPham = bt.maSanPham
//     JOIN ThuocTinh tt ON bt.maSKU = tt.maSKU
//
//     GROUP BY
//         sp.maSanPham,
//         sp.tenSanPham,
//         sp.soLuong,
//         sp.gia,
//         bt.maSKU,
//         bt.gia,
//         bt.soLuong,
//         bt.trangThai
//
//    """, nativeQuery = true)
//    List<SanPhamFullDTO> getSanPhamFullDTOs();


    @Query(value = """
    SELECT 
        sp.maSanPham,
        sp.tenSanPham,
        sp.soLuong,
        sp.gia,
        bt.maSKU,
        bt.gia,
        bt.soLuong,
        bt.trangThai,
        STRING_AGG(tt.tenThuocTinh + ': ' + tt.tenThuocTinhBienThe, ', ') AS thuocTinh
    FROM SanPham sp
    JOIN BienTheSanPham bt ON sp.maSanPham = bt.maSanPham
    JOIN ThuocTinh tt ON bt.maSKU = tt.maSKU
    GROUP BY 
        sp.maSanPham,
        sp.tenSanPham,
        sp.soLuong,
        sp.gia,
        bt.maSKU,
        bt.gia,
        bt.soLuong,
        bt.trangThai
""", nativeQuery = true)
    List<Object[]> getSanPhamFullDTOs();


    @Query(value = """
     SELECT 
        sp.maSanPham,
        sp.tenSanPham,
        sp.soLuong,
        sp.gia,
        bt.maSKU,
        bt.gia,
        bt.soLuong,
        bt.trangThai,
        STRING_AGG(tt.tenThuocTinh + ': ' + tt.tenThuocTinhBienThe, ', ') AS thuocTinh
    FROM SanPham sp
    JOIN BienTheSanPham bt ON sp.maSanPham = bt.maSanPham
    JOIN ThuocTinh tt ON bt.maSKU = tt.maSKU
     WHERE sp.maSanPham = :maSanPham
    GROUP BY 
        sp.maSanPham,
        sp.tenSanPham,
        sp.soLuong,
        sp.gia,
        bt.maSKU,
        bt.gia,
        bt.soLuong,
        bt.trangThai

    """, nativeQuery = true)
    List<Object[]> getSanPhamFullDTOsByMaSanPham(@Param("maSanPham") String maSanPham);


}


