package com.example.java6.ASM.Repository;

import com.example.java6.ASM.Model.Favorite;
import com.example.java6.ASM.Model.SanPham;
import com.example.java6.ASM.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserAndSanPham(User user, SanPham sanPham);
    Optional<Favorite> findByUserAndSanPham(User user, SanPham sanPham);
    List<Favorite> findAllByUser(User user);
    Long countBySanPham(SanPham sanPham);

//    @Query("SELECT COUNT(f) > 0 FROM Favorite f WHERE f.user.id = :userId AND f.sanPham.id = :sanPhamId")
//    boolean existsByUserAndSanPham(@Param("userId") Integer userId, @Param("sanPhamId") Integer sanPhamId);
//
//    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.sanPham.id = :sanPhamId")
//    Optional<Favorite> findByUserAndSanPham(@Param("userId") Integer userId, @Param("sanPhamId") Integer sanPhamId);
//
//
//
//    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
//    List<Favorite> findAllByUser(@Param("userId") Integer userId);
//
//    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.sanPham.id = :sanPhamId")
//    Long countBySanPham(@Param("sanPhamId") Integer sanPhamId);



}
