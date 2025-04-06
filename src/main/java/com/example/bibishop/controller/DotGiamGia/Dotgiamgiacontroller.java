package com.example.bibishop.controller.DotGiamGia;


import com.example.bibishop.entity.DotGiamGia;
import com.example.bibishop.repository.DotGiamGiaRepository;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // Sửa từ RestController thành Controller để trả về view
@RequestMapping("/admin/")
@RequiredArgsConstructor
public class Dotgiamgiacontroller {

  private final DotGiamGiaRepository dotGiamGiaRepository;

  @GetMapping("dotgiamgia/list")
  public String listDotGiamGia(
      Model model,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "5") int size) {
    Page<DotGiamGia> dotGiamGiaPage = dotGiamGiaRepository.findAll(PageRequest.of(page - 1, size));
    model.addAttribute("list", dotGiamGiaPage);
    return "admin/dotgiamgia/list";
  }

  @GetMapping("dotgiamgia/view/{id}")
  public String viewAndUpdateDotGiamGia(@PathVariable int id, Model model) {
    dotGiamGiaRepository.findById((long) id).ifPresent(dotGiamGia ->
        model.addAttribute("updateDotGiamGia", dotGiamGia));
    return "admin/DotGiamGia/viewAndUpdate";
  }

  @GetMapping("dotgiamgia/add")
  public String addDotGiamGia(Model model) {
    model.addAttribute("newDotGiamGia", new DotGiamGia());
    return "admin/DotGiamGia/add";
  }

  @PostMapping("dotgiamgia/add")
  public String addDotGiamGia(
      @ModelAttribute("newDotGiamGia") DotGiamGia dotGiamGia,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("message", "Có lỗi khi nhập thông tin: " + bindingResult.getFieldError());
      redirectAttributes.addFlashAttribute("messageType", "error");
      return "redirect:/admin/dotgiamgia/add";
    }

    try {
      DotGiamGia newDotGiamGia = new DotGiamGia();
      newDotGiamGia.setTen(dotGiamGia.getTen());
      newDotGiamGia.setMa(dotGiamGia.getMa());
      newDotGiamGia.setGiaTriGiam(dotGiamGia.getGiaTriGiam());
      newDotGiamGia.setNgayBatDau(dotGiamGia.getNgayBatDau());
      newDotGiamGia.setNgayKetThuc(dotGiamGia.getNgayKetThuc());
      newDotGiamGia.setNgayTao(LocalDate.now().atStartOfDay());
      newDotGiamGia.setTrangThai(1); // Mặc định hoạt động
      newDotGiamGia.setLoaiVoucher(0); // Chỉ giảm theo giá trị

      dotGiamGiaRepository.save(newDotGiamGia);
      redirectAttributes.addFlashAttribute("message", "Thêm đợt giảm giá thành công!");
      redirectAttributes.addFlashAttribute("messageType", "success");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Lỗi khi thêm đợt giảm giá: " + e.getMessage());
      redirectAttributes.addFlashAttribute("messageType", "error");
      return "redirect:/admin/dotgiamgia/add";
    }
    return "redirect:/admin/dotgiamgia/list";
  }

  @PostMapping("dotgiamgia/delete/{id}")
  public String deleteDotGiamGia(@PathVariable int id, RedirectAttributes redirectAttributes) {
    try {
      Optional<DotGiamGia> dotGiamGiaOpt = dotGiamGiaRepository.findById((long) id);
      if (dotGiamGiaOpt.isPresent()) {
        DotGiamGia dotGiamGia = dotGiamGiaOpt.get();
        dotGiamGia.setTrangThai(dotGiamGia.getTrangThai() == 1 ? 0 : 1);
        dotGiamGiaRepository.save(dotGiamGia);
        redirectAttributes.addFlashAttribute("message", "Cập nhật trạng thái thành công!");
        redirectAttributes.addFlashAttribute("messageType", "success");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Lỗi khi cập nhật trạng thái: " + e.getMessage());
      redirectAttributes.addFlashAttribute("messageType", "error");
    }
    return "redirect:/admin/dotgiamgia/list";
  }

  @PostMapping("dotgiamgia/update/{id}")
  public String updateDotGiamGia(
      @PathVariable int id,
      @ModelAttribute("updateDotGiamGia") DotGiamGia dotGiamGia,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("message", "Có lỗi khi nhập thông tin: " + bindingResult.getFieldError());
      redirectAttributes.addFlashAttribute("messageType", "error");
      return "redirect:/admin/DotGiamGia/view/" + id;
    }

    try {
      Optional<DotGiamGia> existingDotGiamGia = dotGiamGiaRepository.findById((long) id);
      if (existingDotGiamGia.isPresent()) {
        DotGiamGia updatedDotGiamGia = existingDotGiamGia.get();
        updatedDotGiamGia.setTen(dotGiamGia.getTen());
        updatedDotGiamGia.setMa(dotGiamGia.getMa());
        updatedDotGiamGia.setGiaTriGiam(dotGiamGia.getGiaTriGiam());
        updatedDotGiamGia.setNgayBatDau(dotGiamGia.getNgayBatDau());
        updatedDotGiamGia.setNgayKetThuc(dotGiamGia.getNgayKetThuc());
        updatedDotGiamGia.setNgaySua(LocalDate.now().atStartOfDay());
        updatedDotGiamGia.setLoaiVoucher(0); // Chỉ giảm theo giá trị

        dotGiamGiaRepository.save(updatedDotGiamGia);
        redirectAttributes.addFlashAttribute("message", "Cập nhật đợt giảm giá thành công!");
        redirectAttributes.addFlashAttribute("messageType", "success");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "Lỗi khi cập nhật đợt giảm giá: " + e.getMessage());
      redirectAttributes.addFlashAttribute("messageType", "error");
      return "redirect:/admin/DotGiamGia/view/" + id;
    }
    return "redirect:/admin/dotgiamgia/list";
  }
}