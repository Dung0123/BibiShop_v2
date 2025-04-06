package com.example.bibishop.service;


import com.example.bibishop.constant.Constant;
import com.example.bibishop.dto.admin.CreateKhachHangRequest;
import com.example.bibishop.entity.KhachHang;
import com.example.bibishop.repository.KhachHangRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangRepository khachHangRepository;

    public KhachHangServiceImpl(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    @Override
    public List<KhachHang> getAllCustomers() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getCustomerById(Integer id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public void register(CreateKhachHangRequest request) {
        validateCustomer(request);
        KhachHang khachHang = new KhachHang();
        khachHang.setEmail(request.getEmail());
        khachHang.setTen(request.getFullName());
        khachHang.setSdt(request.getPhone());
        khachHang.setPassword(request.getPassword());
        khachHang.setTrangThai(1);
        khachHangRepository.save(khachHang);
    }
    @Override
    public void updateProfile(CreateKhachHangRequest request) {
        validateCustomer(request);
        KhachHang khachHang = new KhachHang();
        khachHang.setId(request.getId());
        khachHang.setTen(request.getFullName());
        khachHang.setSdt(request.getPhone());
        khachHang.setAddress(request.getAddress());
        khachHangRepository.save(khachHang);
    }
    @Override
    public void deleteCustomer(Integer id) {
        khachHangRepository.deleteById(id);
    }

 @Override
    public Page<KhachHang> adminListUserPages(String fullName, String phone, String email, Integer page) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Constant.LIMIT_SIZE, Sort.by("ngay_tao").descending());
        return khachHangRepository.adminListUserPages( fullName!=null? fullName.trim():null, phone!=null ? phone.trim():null, email!=null?email.trim():null, pageable);
    }
    @Override
    public void saveCustomer(CreateKhachHangRequest request) {
        validateCustomer(request);
        KhachHang khachHang = new KhachHang();
        khachHang.setEmail(request.getEmail());
        khachHang.setTen(request.getFullName());
        khachHang.setHo(request.getFirstName());
        khachHang.setTenDem(request.getMidName());
        khachHang.setSdt(request.getPhone());
        khachHang.setPassword(request.getPassword());
        khachHang.setTrangThai(1);
        khachHang.setGioiTinh(request.getGender());
        khachHang.setNgayTao(LocalDateTime.now());
        khachHang.setNgaySua(LocalDateTime.now());
        khachHang.setNgaySinh(request.getDob().toInstant());
//        khachHang.setTaiKhoan(request.getAccount());
        khachHang.setVaiTro(3);
        khachHangRepository.save(khachHang);
    }

    @Override
    public void updateCustomer(CreateKhachHangRequest request) {
        validateCustomer(request);
        KhachHang khachHang = khachHangRepository.findById(request.getId()).orElse(new KhachHang());
        khachHang.setId(request.getId());
        khachHang.setEmail(request.getEmail());
//        khachHang.setTaiKhoan(request.getAccount());
        khachHang.setNgaySinh(request.getDob().toInstant());
        khachHang.setSdt(request.getPhone());
        khachHang.setTen(request.getFullName());
        khachHang.setHo(request.getFirstName());
        khachHang.setTenDem(request.getMidName());
        khachHang.setTrangThai(request.getStatus());
        // 3: KHACH HANG
        khachHang.setGioiTinh(request.getGender());
        khachHang.setNgaySua(LocalDateTime.now());
        khachHangRepository.save(khachHang);
    }



    private void validateCustomer(CreateKhachHangRequest request) {
        if (request.getId() != null) {
            // validate update
//            if (khachHangRepository.existsByTaiKhoanAndIdNot(request.getAccount(), request.getId())) {
//                throw new RuntimeException("Tài khoản đã tồn tại!");
//            }
            if (khachHangRepository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
                throw new RuntimeException("Email đã tồn tại!");
            }
            if (khachHangRepository.existsBySdtAndIdNot(request.getPhone(), request.getId()))
                throw new RuntimeException("Số điện thoại đã tồn tại!");
        }else{
            // validate create
//            if(khachHangRepository.existsByTaiKhoan(request.getAccount())){
//                throw new RuntimeException("Tài khoản đã tồn tại!");
//            }
            if(khachHangRepository.existsByEmail(request.getEmail())){
                throw new RuntimeException("Email đã tồn tại!");
            }
            if(khachHangRepository.existsBySdt(request.getPhone())){
                throw new RuntimeException("Số điện thoại đã tồn tại!");
            }
        }
    }
}

