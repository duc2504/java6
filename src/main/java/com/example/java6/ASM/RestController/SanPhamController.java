package com.example.java6.ASM.RestController;




import com.example.java6.ASM.DTO.SanPhamDTO;
import com.example.java6.ASM.DTO.SanPhamFullDTO;
import com.example.java6.ASM.Service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SanPhamController {
//    admin1 123456
//    user1 123456
//    | Method | Endpoint          | HTTP Code | Ý nghĩa                |
//            | ------ | ----------------- | --------- | ---------------------- |
//            | GET    | localhost:8081/api/san-pham  | 200       | Lấy danh sách sản phẩm |
//            | GET    | localhost:8081/api/san-pham/1 | 200/404   | Lấy theo ID            |
//            | POST   | localhost:8081/api/san-pham  | 201       | Thêm mới               |
//            | PUT    | localhost:8081/api/san-pham/1 | 200/404   | Cập nhật               |
//            | DELETE | localhost:8081/api/san-pham/1 | 204/404   | Xóa sản phẩm           |
//    localhost:8081/api/san-pham/full/1
    //    localhost:8081/api/san-pham/full

//    @PreAuthorize("hasRole('Admin')")     // đúng với DB
//    @PreAuthorize("hasRole('User')")
//    @PreAuthorize("hasRole('Moderator')")

    private  final SanPhamService sanPhamService;


    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public ResponseEntity<List<SanPhamDTO>> getAll() {
        List<SanPhamDTO> list = sanPhamService.getAll();
        return ResponseEntity.ok(list);
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<SanPhamDTO> getById(@PathVariable Integer id) {
        SanPhamDTO dto = sanPhamService.getById(id);
        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<SanPhamDTO> create(@RequestBody SanPhamDTO dto) {
        SanPhamDTO created = sanPhamService.create(dto);
        URI location = URI.create("/api/san-pham/" + created.getMaSanPham());
        return ResponseEntity.created(location).body(created); // HTTP 201
    }


    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<SanPhamDTO> update(@PathVariable Integer id, @RequestBody SanPhamDTO dto) {
        SanPhamDTO updated = sanPhamService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sanPhamService.delete(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }


    @PreAuthorize("hasRole('User')")
    @GetMapping("/full")
    public List<SanPhamFullDTO> getSanPhamFullDTOs() {
        return sanPhamService.getAllSanPhamFullDTOs();
    }



    @PreAuthorize("hasRole('User')")
    @GetMapping("/full/{maSanPham}")
    public List<SanPhamFullDTO> getSanPhamFullByMaSanPham(@PathVariable("maSanPham") String maSanPham) {
        return sanPhamService.getSanPhamFullDTOsByMaSanPham(maSanPham);
    }



}
