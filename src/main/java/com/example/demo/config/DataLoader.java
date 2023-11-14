//package com.example.demo.config;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepository;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//	private final UserRepository userRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public DataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		// Create users without roles
//
//		// Admin user
//		User adminUser = new User();
//		adminUser.setUsername("admin1");
//		adminUser.setPassword(passwordEncoder.encode("admin"));
//		userRepository.save(adminUser);
//
//		// Regular user
//		User regularUser = new User();
//		regularUser.setUsername("user");
//		regularUser.setPassword(passwordEncoder.encode("user"));
//		userRepository.save(regularUser);
//	}
//}
