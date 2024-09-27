package com.dms.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig   {

  @Bean
  public SecurityFilterChain customFilterChain1(HttpSecurity http) throws Exception {
   
    http
			.authorizeHttpRequests(req -> 
       req.requestMatchers("/**").permitAll().anyRequest().authenticated());
    return http.build();
  }
  
}
