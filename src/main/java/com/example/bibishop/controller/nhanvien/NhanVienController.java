package com.example.bibishop.controller.nhanvien;

//import com.poly.admin.config.exeption.BadRequestException;


import com.example.bibishop.dto.ResponseMessage;
import com.example.bibishop.dto.admin.CreateNhanVienRequest;
import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/admin/nhanvien")
@RequiredArgsConstructor
public class NhanVienController {
    private final NhanVienService nhanVienService;

    @GetMapping("list")
    public String listEmployee(Model model,
                            @RequestParam(required = false) String fullName,
                            @RequestParam(required = false) String phone,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String address,
                            @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<NhanVien> users = nhanVienService.adminListUserPages(fullName, phone, email, page);

        model.addAttribute("list", users.getContent());
        model.addAttribute("totalPages", users.getTotalPages());
        model.addAttribute("currentPage", users.getPageable().getPageNumber() + 1);
            return "admin/nhanvien/list";
    }

    @GetMapping("/create")
    public String showAddEmployeeForm(Model model) {
        return "admin/nhanvien/add";

    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody CreateNhanVienRequest request) {
        try {
            NhanVien nhanVien = nhanVienService.saveEmployee(request);
            return ResponseEntity.ok().body(new ResponseMessage("Thêm nhân viên thành công", true)); // Trả về thông báo và success
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Có lỗi xảy ra khi thêm nhân viên", false)); // Trả về thông báo lỗi
        }
    }


    @GetMapping("/{id}")
    public String showUpdateEmployeeForm(@PathVariable("id") Integer id, Model model) {
        NhanVien nhanVien = nhanVienService.getEmployeeById(id);
        model.addAttribute("nhanVien", nhanVien);
        return "admin/nhanvien/update";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") Integer id, @RequestBody CreateNhanVienRequest nhanVien) {
        nhanVien.setId(id);
        nhanVienService.updateNhanVien(nhanVien);
        return ResponseEntity.ok("Cập nhật thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Integer id) {
        nhanVienService.deleteEmployee(id);
        return ResponseEntity.ok("Xóa thành công");
    }
}