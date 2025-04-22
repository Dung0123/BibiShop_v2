package com.example.bibishop.config;

import com.example.bibishop.entity.NhanVien;
import com.example.bibishop.repository.NhanVienRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private NhanVienRepository userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsServiceImpl getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
//
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/customer/**").hasRole("CUSTOMER")
						.requestMatchers("/seller/**").hasRole("SELLER")
       					.requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
								.anyRequest().authenticated()
//						.requestMatchers("/**").permitAll()

				)
				.formLogin(form -> form
						.successHandler(new AuthenticationSuccessHandler() {
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
									Authentication authentication) throws IOException, ServletException {
								HttpSession session = request.getSession();
								session.setAttribute("status", "success");
								session.setAttribute("message", "Đăng nhập thành công!");

								NhanVien user = userRepo.findNhanVienByEmail(authentication.getName());
//								String redirectURL = request.getContextPath();
//
//								redirectURL = "v1/admin/index";

								response.sendRedirect("/index");
							}
						})
						.failureHandler(new AuthenticationFailureHandler() {
							@Override
							public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
									AuthenticationException exception) throws IOException, ServletException {
								HttpSession session = request.getSession();

								if (exception.getMessage().equals("Bad credentials")) {
									session.setAttribute("status", "error");
									session.setAttribute("message", "Sai tài khoản hoặc mật khẩu!");
								} else if (exception.getMessage().equals("User is disabled")) {
									session.setAttribute("status", "error");
									session.setAttribute("message", "Tài khoản đã bị khóa!");
								}

								response.sendRedirect("/login");
							}
						})
						.loginPage("/login")
						.defaultSuccessUrl("/index", true)
				)
				.logout(logout -> logout
						.logoutSuccessHandler(new LogoutSuccessHandler() {
							@Override
							public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
									Authentication authentication) throws IOException, ServletException {
								HttpSession httpSession = request.getSession();

								if (authentication != null) {
									httpSession.setAttribute("status", "logout-success");
									response.sendRedirect("/login?logoutSuccess");
								} else {
									response.sendRedirect("/login?doLogin");
								}
							}
						})
				)
				.csrf(csrf -> csrf.disable());

		return http.build();
	}
}