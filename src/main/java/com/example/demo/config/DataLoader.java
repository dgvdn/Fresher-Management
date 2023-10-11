//package com.example.demo.config;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.entity.Role;
//import com.example.demo.entity.User;
//import com.example.demo.repository.RoleRepository;
//import com.example.demo.repository.UserRepository;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//	private final UserRepository userRepository;
//	private final RoleRepository roleRepository;
//	private final PasswordEncoder passwordEncoder;
//
//	public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		// Find existing roles
//		Role roleAdmin = roleRepository.findByName("ADMIN");
//		Role roleUser = roleRepository.findByName("USER");
//
//		Set<Role> adminRoles = new HashSet<>();
//		adminRoles.add(roleAdmin);
//		User adminUser = new User(3L, "admin1", passwordEncoder.encode("admin"), adminRoles);
//		userRepository.save(adminUser);
//
//		Set<Role> userRoles = new HashSet<>();
//		userRoles.add(roleUser);
//		User regularUser = new User(4L, "user", passwordEncoder.encode("user"), userRoles);
//		userRepository.save(regularUser);
//	}
//}