package com.example.bibishop.config;


import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private NhanVienRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		NhanVien user = this.userRepo.findNhanVienByEmail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("No user is available for this email address.");
		}
		
		return new CustomUserDetails(user);
	}

}
