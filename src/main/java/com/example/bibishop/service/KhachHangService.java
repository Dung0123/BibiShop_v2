package com.example.bibishop.service;


import com.example.bibishop.dto.admin.CreateKhachHangRequest;
import com.example.bibishop.entity.KhachHang;
import java.util.List;
import org.springframework.data.domain.Page;

public interface KhachHangService {

  List<KhachHang> getAllCustomers();

  KhachHang getCustomerById(Integer id);

  void saveCustomer(CreateKhachHangRequest khachHang);

  void register(CreateKhachHangRequest khachHang);

  void updateProfile(CreateKhachHangRequest khachHang);

  void updateCustomer(CreateKhachHangRequest khachHang);

  void deleteCustomer(Integer id);

  Page<KhachHang> adminListUserPages(String fullName, String phone, String email, Integer page);
}