package com.example.bibishop.service;


import com.example.bibishop.dto.admin.CreateNhanVienRequest;
import com.example.bibishop.entity.NhanVien;
import java.util.List;
import org.springframework.data.domain.Page;

public interface NhanVienService {

  List<NhanVien> getAllEmployees();

  Page<NhanVien> adminListUserPages(String fullName, String phone, String email, Integer page);

  NhanVien getEmployeeById(Integer id);

  NhanVien saveEmployee(CreateNhanVienRequest nhanVien);

  NhanVien updateNhanVien(CreateNhanVienRequest nhanVien);

  void deleteEmployee(Integer id);
}
