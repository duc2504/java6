package com.example.java6.ASM.Service;



import com.example.java6.ASM.DTO.FavoriteDTO;
import com.example.java6.ASM.Model.*;
import com.example.java6.ASM.Repository.*;
import com.example.java6.ASM.Service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepo;
    private final SanPhamRepository sanPhamRepo;


    public void addFavorite(Integer sanPhamId, User user) {
        if (user.getRole().getRoleName().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin không được đánh dấu yêu thích");
        }
        SanPham sp = sanPhamRepo.findById(sanPhamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (favoriteRepo.existsByUserAndSanPham(user, sp)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Đã thích sản phẩm này rồi");
        }
        Favorite f = new Favorite();
        f.setUser(user);
        f.setSanPham(sp);
        f.setCreated_at(LocalDateTime.now());
        favoriteRepo.save(f);
    }


    public void removeFavorite(Integer sanPhamId, User user) {
        if (user.getRole().getRoleName().equals("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin không được bỏ yêu thích");
        }
        SanPham sp = sanPhamRepo.findById(sanPhamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Favorite f = favoriteRepo.findByUserAndSanPham(user, sp)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        favoriteRepo.delete(f);
    }


    public List<FavoriteDTO> getMyFavorites(User user) {
        return favoriteRepo.findAllByUser(user).stream().map(fav -> {
            FavoriteDTO dto = new FavoriteDTO();
            dto.setId(fav.getId());
            dto.setSanPhamId(fav.getSanPham().getMaSanPham());
            dto.setTenSanPham(fav.getSanPham().getTenSanPham());
            dto.setCreatedAt(fav.getCreated_at());
            return dto;
        }).collect(Collectors.toList());
    }


    public Long countFavorites(Integer sanPhamId) {
        SanPham sp = sanPhamRepo.findById(sanPhamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return favoriteRepo.countBySanPham(sp);
    }
}



