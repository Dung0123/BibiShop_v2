package com.example.bibishop.controller.khachhang;


import com.example.bibishop.dto.admin.CreateKhachHangRequest;
import com.example.bibishop.entity.KhachHang;
import com.example.bibishop.service.KhachHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/khachhang")
@RequiredArgsConstructor
public class KhachHangController {

    private final KhachHangService khachHangService;

    @GetMapping("list")
    public String listEmployee(Model model,
                               @RequestParam( required = false) String fullName,
                               @RequestParam( required = false) String phone,
                               @RequestParam( required = false) String email,
                               @RequestParam( required = false) String address,
                               @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<KhachHang> users = khachHangService.adminListUserPages(fullName, phone, email, page);
        model.addAttribute("list", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("currentPage", users.getPageable().getPageNumber() + 1);
        return "admin/khachhang/list";
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Integer id) {
        khachHangService.deleteCustomer(id);
        return ResponseEntity.ok("Xóa thành công");
    }
    @GetMapping("/create")
    public String showAddEmployeeForm(Model model) {
        return "admin/khachhang/add";
    }

    @PostMapping("/create")
    public String addEmployee(@RequestBody CreateKhachHangRequest request) {

        khachHangService.saveCustomer(request);
        return "redirect:/admin/khachhang/list";
    }
    @GetMapping("/{id}")
    public String showUpdateEmployeeForm(@PathVariable("id") Integer id, Model model) {
        KhachHang khachHang = khachHangService.getCustomerById(id);
        model.addAttribute("khachHang", khachHang);
        return "admin/khachhang/update";
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("id") Integer id, @RequestBody CreateKhachHangRequest request) {
        request.setId(id);
        khachHangService.updateCustomer(request);
        return ResponseEntity.ok("Cập nhật thành công");
    }
}