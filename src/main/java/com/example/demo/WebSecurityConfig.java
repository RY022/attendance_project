package com.example.demo;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	            	.antMatchers("/menupage").permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                	.loginPage("/loginpage")
	                	.loginProcessingUrl("file:///C:/pleiades/2024-09/workspace/Attendance_project/src/main/project/html/loginpage.html")
	                	.usernameParameter("userID")
	                	.passwordParameter("password")
	                	.defaultSuccessUrl("file:///C:/pleiades/2024-09/workspace/Attendance_project/src/main/project/menupage.html")
	                	.failureUrl("/menupage");
	    }

}
