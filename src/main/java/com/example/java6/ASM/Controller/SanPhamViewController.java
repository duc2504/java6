package com.example.java6.ASM.Controller;

import com.example.java6.ASM.Model.SanPham;
import com.example.java6.ASM.Repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/san-pham")
@RequiredArgsConstructor

public class SanPhamViewController {

    private final SanPhamRepository sanPhamRepository;
//    OAuth2AuthenticationToken authentication
    @GetMapping
    public String list(Model model, Authentication authentication) {
        if (authentication != null) {
            if (authentication instanceof OAuth2AuthenticationToken oauth2) {
                OAuth2User user = oauth2.getPrincipal();
                String email = user.getAttribute("email");
                String name = user.getAttribute("name");
                model.addAttribute("userEmail", email);
                model.addAttribute("userName", name);
            } else if (authentication.getPrincipal() instanceof UserDetails userDetails) {
                model.addAttribute("userEmail", userDetails.getUsername());
                model.addAttribute("userName", ""); // nếu bạn có field name trong UserEntity thì ép kiểu vào
            }
        }
        model.addAttribute("list", sanPhamRepository.findAll());
        return "sanpham/list";
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("sanPham", new SanPham());
        return "sanpham/form";
    }
    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/save")
    public String save(@ModelAttribute("sanPham") SanPham sanPham) {
        sanPhamRepository.save(sanPham);
        return "redirect:/san-pham";
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamRepository.findById(id).orElse(null);
        model.addAttribute("sanPham", sanPham);
        return "sanpham/form";
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sanPhamRepository.deleteById(id);
        return "redirect:/san-pham";
    }
}
