package com.example.bibishop.restcontroller;

import com.example.bibishop.common.Appcontants;
import com.example.bibishop.dto.SanPhamChiTietDTO;
import com.example.bibishop.dto.SanPhamChiTietFiterDTO;
import com.example.bibishop.dto.SanPhamDTO;
import com.example.bibishop.dto.SanPhamFiterDTO;
import com.example.bibishop.entity.SanPhamEntity;
import com.example.bibishop.repository.sanpham.SanPhamRepository;
import com.example.bibishop.service.ChatLieuService;
import com.example.bibishop.service.sanpham.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/sanpham")
@RequiredArgsConstructor
public class SanPhamController {

    private final SanPhamService sanPhamService;
    private final SanPhamRepository sanPhamRepository;
    private final DanhMucService danhMucService;
    private final MauSacService mauSacService;
    private final ChatLieuService chatLieuService;
    private final KichCoService kichCoService;
    private final SanPhamChiTietService sanPhamChiTietService;

    // Trang chính (hỗ trợ phân trang và filter)
    @GetMapping("/getAll")
    public String viewSanPham(
            @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
            @ModelAttribute("filterForm") @Valid SanPhamFiterDTO filterForm,
            Model model) {
        Page<SanPhamDTO> sanPhamPage = sanPhamService.getAllSanPham(pageNo, pageSize, filterForm);
        model.addAttribute("list", sanPhamPage.getContent());
        model.addAttribute("sanPham", new SanPhamDTO());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
        return "admin/sanpham/sanpham";
    }

    @GetMapping("/view/{id}")
    public String viewSanPhamById(@PathVariable Integer id,
                                  Model model,
                                  @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                  @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
                                  @ModelAttribute("filterForm") @Valid SanPhamFiterDTO filterForm

    ) {
        System.out.println("ID nhận được: " + id);
        Optional<SanPhamEntity> sanPham = sanPhamRepository.findById(id);
        model.addAttribute("sanPham", sanPham.orElse(new SanPhamEntity()));
        model.addAttribute("list", sanPhamService.getAllSanPham());
        Page<SanPhamDTO> sanPhamPage = sanPhamService.getAllSanPham(pageNo, pageSize, filterForm);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
        return "admin/sanpham/sanpham";
    }

    // Thêm mới
    @PostMapping("/add")
    public String addSanPham(@ModelAttribute("sanPham") SanPhamDTO dto,
                             Model model,
                             @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                             @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
                             @ModelAttribute("filterForm") @Valid SanPhamFiterDTO filterForm
    ) {
        if (dto.getTenSanPham() == null || dto.getTenSanPham().trim().isEmpty()) {
            model.addAttribute("error", "Tên sản phẩm không được để trống.");
            prepareModel(model, pageNo, pageSize, filterForm);
            return "admin/sanpham/sanpham";

        } else if (sanPhamService.checkProductName(dto.getTenSanPham())) {
            model.addAttribute("error", "Tên sản phẩm đã tồn tại.");
            Page<SanPhamDTO> sanPhamPage = sanPhamService.getAllSanPham(pageNo, pageSize, filterForm);
            prepareModel(model, pageNo, pageSize, filterForm);
            return "admin/sanpham/sanpham";

        } else {
            sanPhamService.addSanPham(dto);
            Page<SanPhamDTO> sanPhamPage = sanPhamService.getAllSanPham(pageNo, pageSize, filterForm);
            prepareModel(model, pageNo, pageSize, filterForm);
            return "admin/sanpham/sanpham";
        }
    }

    // Cập nhật
    @PostMapping("/update")
    public String updateSanPham(@ModelAttribute("sanPham") SanPhamDTO dto,
                                Model model,
                                @RequestParam(value = "pageNo", defaultValue = Appcontants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                @RequestParam(value = "pageSize", defaultValue = Appcontants.DEFAULT_TOTAL_NUMBER, required = false) int pageSize,
                                @ModelAttribute("filterForm") @Valid SanPhamFiterDTO filterForm) {

        if (dto.getTenSanPham() == null || dto.getTenSanPham().trim().isEmpty()) {
            model.addAttribute("error", "Tên sản phẩm không được để trống.");
            prepareModel(model, pageNo, pageSize, new SanPhamFiterDTO());
            return "admin/sanpham/sanpham";

        } else if (sanPhamService.checkProductNameForUpdate(dto.getTenSanPham(), dto.getId())) {
            model.addAttribute("error", "Tên sản phẩm đã tồn tại.");
            prepareModel(model, pageNo, pageSize, new SanPhamFiterDTO());
            return "admin/sanpham/sanpham";

        } else {
            sanPhamService.upDateSanPham(dto);
            prepareModel(model, pageNo, pageSize, new SanPhamFiterDTO());
            return "admin/sanpham/sanpham";
        }
    }

    @GetMapping("/SPCT")
    public String addSanPhamChiTiet(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        Page<SanPhamChiTietDTO> pageSPCT = sanPhamChiTietService.getAllSanPhamChiTiet(page, size, new SanPhamChiTietFiterDTO());

        model.addAttribute("pageSPCT", pageSPCT);
        model.addAttribute("listProduct", sanPhamService.getAllSanPham());
        model.addAttribute("listColor", mauSacService.getAllMauSac());
        model.addAttribute("listSize", kichCoService.getAllKichCo());
        model.addAttribute("listMaterial", chatLieuService.getAllChatLieu());
        model.addAttribute("listCategory", danhMucService.getAllDanhMuc());

        return "admin/sanpham/addSPCT";
    }


    private void prepareModel(Model model, int pageNo, int pageSize, SanPhamFiterDTO filterForm) {
        Page<SanPhamDTO> sanPhamPage = sanPhamService.getAllSanPham(pageNo, pageSize, filterForm);
        model.addAttribute("list", sanPhamPage.getContent()); // lấy nội dung trang hiện tại
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", sanPhamPage.getTotalPages());
//        model.addAttribute("filterForm", filterForm != null ? filterForm : new SanPhamFiterDTO());
        model.addAttribute("filterForm", filterForm);
    }


    // Xóa mềm
//    @GetMapping("/delete/{id}")
//    public String deleteSanPham(@PathVariable Long id) {
//        sanPhamService.deleteSanPham(id);
//        return "redirect:/admin/sanpham/getAll";
//    }

    // Reset form
    @GetMapping("/reset")
    public String resetForm() {
        return "redirect:/admin/sanpham/getAll";
    }


}