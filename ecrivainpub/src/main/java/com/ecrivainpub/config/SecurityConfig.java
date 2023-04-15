package com.ecrivainpub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@PropertySource("application.properties")
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.requestMatchers("/admin/**")
		.hasRole("ADMIN")
		.and()
		.authorizeHttpRequests()
		.requestMatchers("/**")
		.permitAll();
		http.httpBasic();
		http.csrf().disable();
		return http.build();
		
	}
	
	@Bean
    InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("root")
            .password("nonono")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
	
}
