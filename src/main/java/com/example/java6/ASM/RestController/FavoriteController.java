package com.example.java6.ASM.RestController;
import com.example.java6.ASM.DTO.FavoriteDTO;
import com.example.java6.ASM.Model.Favorite;
import com.example.java6.ASM.Model.SanPham;
import com.example.java6.ASM.Model.User;
import com.example.java6.ASM.Service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FavoriteController {

//    admin1 123456
//    user1 123456
//1. POST    localhost:8081/api/items/{{id}}/favorites          ✅ Đánh dấu
//2. DELETE  localhost:8081/api/items/{id sản phẩm}/favorites            ❌ Bỏ đánh dấu
//3. GET       localhost:8081/api/users/me/favorites                                📋 Xem danh sách của mình
//4. GET       localhost:8081/api/items/{id sản phẩm}/favorites/count 🔢 Xem tổng lượt yêu thích
    private final FavoriteService favoriteService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/items/{id}/favorites")
    public ResponseEntity<?> addFavorite(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) {
        favoriteService.addFavorite(id, user);
        return ResponseEntity.ok("Đã thêm yêu thích");
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/items/{id}/favorites")
    public ResponseEntity<?> removeFavorite(@PathVariable("id") Integer id, @AuthenticationPrincipal User user) {
        favoriteService.removeFavorite(id, user);
        return ResponseEntity.ok("Đã bỏ yêu thích");
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/users/me/favorites")
    public ResponseEntity<List<FavoriteDTO>> getMyFavorites(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(favoriteService.getMyFavorites(user));
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/items/{id}/favorites/count")
    public ResponseEntity<Long> countFavorites(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(favoriteService.countFavorites(id));
    }

}
