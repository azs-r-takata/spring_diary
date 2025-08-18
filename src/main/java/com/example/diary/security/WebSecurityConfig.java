package com.example.diary.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		    .authorizeHttpRequests((requests) -> requests
		    	.requestMatchers("/diary/delete/**", "/diary/new", "/diary/edit/**", "/diary/save", "/diary/update").hasRole("ADMIN")
		    	.requestMatchers("/login", "/register", "/registing", "/diary/**", "/resources/**").permitAll()
		    	.anyRequest().authenticated()
		    )
		    .formLogin((form) -> form
		        .loginPage("/login")
		        .loginProcessingUrl("/login")
		        .defaultSuccessUrl("/diary?loggedIn")
		        .failureUrl("/login?error")
		        .permitAll()
		    )
		    .logout((logout) -> logout
		        .logoutSuccessUrl("/?loggedOut")
		        .permitAll()
		    );
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
