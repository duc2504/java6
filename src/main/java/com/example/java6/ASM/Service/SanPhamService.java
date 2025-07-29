package com.example.java6.ASM.Service;

import com.example.java6.ASM.DTO.BienTheSanPhamDTO;
import com.example.java6.ASM.DTO.SanPhamDTO;
import com.example.java6.ASM.DTO.SanPhamFullDTO;
import com.example.java6.ASM.Model.SanPham;
import com.example.java6.ASM.Repository.SanPhamRepository;
import com.example.java6.ASM.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SanPhamService {

    @Autowired
    private  SanPhamRepository sanPhamRepo;

    private SanPhamDTO convertToDTO(SanPham sp) {
        return new SanPhamDTO(sp.getMaSanPham(), sp.getTenSanPham(), sp.getSoLuong(), sp.getGia(), sp.getTrangThai());
    }

    private SanPham convertToEntity(SanPhamDTO dto) {
        SanPham sp = new SanPham();
        sp.setMaSanPham(dto.getMaSanPham());
        sp.setTenSanPham(dto.getTenSanPham());
        sp.setSoLuong(dto.getSoLuong());
        sp.setGia(dto.getGia());
        sp.setTrangThai(dto.getTrangThai());
        return sp;
    }


    public List<SanPhamDTO> getAll() {
        return sanPhamRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public SanPhamDTO getById(Integer id) {
        SanPham sp = sanPhamRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với ID: " + id));
        return convertToDTO(sp);
    }


    public SanPhamDTO create(SanPhamDTO dto) {
        SanPham saved = sanPhamRepo.save(convertToEntity(dto));
        return convertToDTO(saved);
    }


    public SanPhamDTO update(Integer id, SanPhamDTO dto) {
        SanPham existing = sanPhamRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm với ID: " + id));

        existing.setTenSanPham(dto.getTenSanPham());
        existing.setSoLuong(dto.getSoLuong());
        existing.setGia(dto.getGia());
        existing.setTrangThai(dto.getTrangThai());

        return convertToDTO(sanPhamRepo.save(existing));
    }


    public void delete(Integer id) {
        if (!sanPhamRepo.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm với ID: " + id);
        }
        sanPhamRepo.deleteById(id);
    }

//    public List<SanPhamFullDTO> getAllSanPhamFullDTOs() {
//        return sanPhamRepo.getSanPhamFullDTOs();
//    }

//        public List<SanPhamFullDTO> getSanPhamFullDTOsByMaSanPham(String maSanPham) {
//        return sanPhamRepo.getSanPhamFullDTOsByMaSanPham(maSanPham);
//    }

    public List<SanPhamFullDTO> getAllSanPhamFullDTOs() {
        List<Object[]> rows = sanPhamRepo.getSanPhamFullDTOs();
        return convertToSanPhamFullDTO(rows);
    }

    public List<SanPhamFullDTO> getSanPhamFullDTOsByMaSanPham(String maSanPham) {
        List<Object[]> rows = sanPhamRepo.getSanPhamFullDTOsByMaSanPham(maSanPham);
        return convertToSanPhamFullDTO(rows);
    }

    // === Extracted common method ===
    private List<SanPhamFullDTO> convertToSanPhamFullDTO(List<Object[]> rows) {
        Map<Integer, SanPhamFullDTO> map = new LinkedHashMap<>();

        for (Object[] r : rows) {
            Integer id = (Integer) r[0];

            map.computeIfAbsent(id, k -> new SanPhamFullDTO(
                    id,
                    (String) r[1],          // tên sản phẩm
                    (Integer) r[2],         // số lượng
                    (BigDecimal) r[3],      // giá
                    new ArrayList<>()
            ));

            BienTheSanPhamDTO bienThe = new BienTheSanPhamDTO(
                    (String) r[4],              // mã biến thể
                    (BigDecimal) r[5],          // giá
                    (Integer) r[6],             // số lượng
                    (Integer) r[7],             // trạng thái
                    (String) r[8]               // thuộc tính
            );

            map.get(id).getBienTheSanPham().add(bienThe);
        }

        return new ArrayList<>(map.values());
    }




}
