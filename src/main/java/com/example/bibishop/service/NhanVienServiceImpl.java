package com.example.bibishop.service;


import com.example.bibishop.constant.Constant;
import com.example.bibishop.dto.admin.CreateNhanVienRequest;
import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.repository.NhanVienRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
//import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<NhanVien> getAllEmployees() {
        return nhanVienRepository.findAll();
    }

    @Override
    public NhanVien getEmployeeById(Integer id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    @Override
    public NhanVien saveEmployee(CreateNhanVienRequest request) {
        if (nhanVienRepository.findNhanVienByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (ObjectUtils.isEmpty(request.getSdt()!=null) && nhanVienRepository.existsBySdt(request.getSdt())) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(request.getId());
        nhanVien.setMaNhanVien(UUID.randomUUID().toString());
        nhanVien.setGioiTinh(request.getGender());
        nhanVien.setEmail(request.getEmail());
        nhanVien.setPasswordRaw(request.getPassword());
        nhanVien.setNgaySua(LocalDateTime.now());
        nhanVien.setNgayTao(LocalDateTime.now());
        nhanVien.setMatKhau(passwordEncoder.encode(request.getPassword()));
        return getNhanVien(request, nhanVien);
    }

    private NhanVien getNhanVien(CreateNhanVienRequest request, NhanVien nhanVien) {
        nhanVien.setNgaySinh(request.getDob().toInstant());
        nhanVien.setSdt(request.getSdt());
        nhanVien.setTen(request.getName());
        nhanVien.setHo(request.getFirstName());
        nhanVien.setTenDem(request.getMidName());
        nhanVien.setTrangThai(request.getStatus());
        nhanVien.setVaiTro(request.getRole());
        nhanVienRepository.save(nhanVien);
        return nhanVien;
    }

    @Override
    public NhanVien updateNhanVien(CreateNhanVienRequest request) {
        if (nhanVienRepository.findNhanVienByIdAndEmail(request.getId(),request.getLoginName()) != null) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }
        if (ObjectUtils.isEmpty(request.getSdt()!=null) && nhanVienRepository.existsBySdtAndIdNot(request.getSdt(), request.getId())) {
            throw new RuntimeException("Số điên thoại đã tồn tại");
        }
        NhanVien nhanVien = nhanVienRepository.findById(request.getId()).orElseThrow();
        nhanVien.setMaNhanVien(UUID.randomUUID().toString());
        nhanVien.setGioiTinh(request.getGender());
        nhanVien.setEmail(request.getEmail());
        nhanVien.setNgaySua(LocalDateTime.now());
        nhanVien.setHo(request.getFirstName());
        nhanVien.setTenDem(request.getMidName());
        nhanVien.setTen(request.getName());
        return getNhanVien(request, nhanVien);
    }

    @Override
    public void deleteEmployee(Integer id) {
        nhanVienRepository.deleteById(id);
    }
    @Override
    public Page<NhanVien> adminListUserPages(String fullName, String phone, String email, Integer page) {
        page--;
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, Constant.LIMIT_SIZE, Sort.by("ngay_tao").descending());
        return nhanVienRepository.adminListUserPages(fullName!=null?fullName.trim():null,
                phone!=null?phone.trim():null, email!=null?email.trim():null, pageable);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        NhanVien user = nhanVienRepository.findNhanVienByEmail(email);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
//        );
//    }
}