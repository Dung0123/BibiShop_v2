package com.example.bibishop.controller.account;


import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @GetMapping("/login")
    public String loginPage(Model m) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            NhanVien user = this.nhanVienRepository.findNhanVienByEmail(auth.getName());
            if (user.getVaiTro() == 0) {
                return "redirect:/index";
            }
            if (user.getVaiTro()==3) {
                return "redirect:/customer/home";
            }

            if (user.getVaiTro()==1) {
                return "redirect:/admin/nhanvien/list";
            }
//            if (user.getRole().equals("ROLE_SELLER")) {
//                return "redirect:/seller/home";
//            }

        }
//        Cookie cookie = WebUtils.getCookie(request, "JWT_TOKEN");
//        if (cookie != null) {
//            authHeader = cookie.getValue();
//            String username = jwtUtil.extractUsername(authHeader);
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = userService.loadUserByUsername(username);
//                if (jwtUtil.isTokenValid(authHeader)) {
//                    log.debug("User - {}", userDetails);
//                    SecurityContext context = SecurityContextHolder.createEmptyContext();
//                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    context.setAuthentication(authToken);
//                    SecurityContextHolder.setContext(context);
//                }
//            }
//        }

        m.addAttribute("title", "Login | StoreWala");
        return "admin/Auth/login";
    }
}
