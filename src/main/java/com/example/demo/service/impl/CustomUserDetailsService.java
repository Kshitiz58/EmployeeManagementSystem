//package com.example.demo.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.demo.model.MyUser;
//import com.example.demo.repository.MyUserRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private MyUserRepository userRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		MyUser user = userRepo.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found." + username));
//		return User
//				.builder()
//				.username(user.getUsername())
//				.password(user.getPassword())
//				.roles(getRoles(user))
//				.build();
//	}
//
//	public String[] getRoles(MyUser user) {
//		if (user.getRole() == null || user.getRole().isEmpty()) {
//			return new String[] { "USER" };
//		}
//		return user.getRole().split(",");
//	}
//
//}
