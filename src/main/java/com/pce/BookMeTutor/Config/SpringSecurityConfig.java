package com.pce.BookMeTutor.Config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/test/**").permitAll()
		.antMatchers(HttpMethod.GET, "/test/**").permitAll()
		.and()
		.csrf().disable()
		.formLogin().disable();
	}
	
	

}
