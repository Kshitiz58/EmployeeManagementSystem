//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import com.example.demo.service.impl.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//	
//	@Autowired
//	public CustomUserDetailsService myUserDetailsService;
//	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//		return http
//				.csrf(AbstractHttpConfigurer:: disable)
//				.authorizeHttpRequests(authz ->{
//					authz.requestMatchers("/assets/**","/error/**","/Homepage/**","/image/**","/login/**").permitAll();
//					authz.requestMatchers("/","/signup").permitAll();
//					authz.requestMatchers("/admin/**").hasRole("ADMIN");
//					authz.requestMatchers("user/**").hasRole("USER");
////					authz.anyRequest().authenticated();
//				})
//				.formLogin(LoginConfig ->{
//					LoginConfig
//					.loginPage("/login")
//					.successHandler(new AuthSuccessHandler())
//					.permitAll();
//				})
//				.build();
//		
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return myUserDetailsService;	
//	} 
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(myUserDetailsService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
//	
//	
//}
