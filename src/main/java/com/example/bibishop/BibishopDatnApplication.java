package com.example.bibishop;

import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.repository.NhanVienRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.example.bibishop.repository",
		"com.example.bibishop.sanpham.repository"  // lưu ý tên phải khớp chính xác
})
public class BibishopDatnApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private NhanVienRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(BibishopDatnApplication.class, args);
	}
	@PostConstruct
	public void inti(){
		String password = "123123";
		String decrypt = passwordEncoder.encode(password);
		System.out.println(decrypt);
	}

	@PostConstruct
	public void createUser(){
		if(userRepo.existsByEmail("admin@gmail.com")) return;
		String hash = passwordEncoder.encode("123123");
		NhanVien createUserRequest = new NhanVien();
		createUserRequest.setTen("admin");
		createUserRequest.setEmail("admin@gmail.com");
		createUserRequest.setSdt("0356123487");
		createUserRequest.setMatKhau(hash);
		userRepo.save(createUserRequest);
		System.out.println(hash);

	}
}
