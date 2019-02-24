
package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomSuccessHandler handler;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
              auth.inMemoryAuthentication().withUser("suresh").password(passwordEncoder.encode("123456")).roles("ADMIN")
              .and().withUser("teja").password(passwordEncoder.encode("12345")).roles("LEAD").and()
              .withUser("venkat").password(passwordEncoder.encode("1234")).roles("USER");
 
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		 http.csrf().disable()
		 .authorizeRequests()
		 .anyRequest().authenticated()
		 .and()
		 .formLogin()
		 .loginPage("/login")
		 .permitAll()
		 .loginProcessingUrl("/check")
		 
		 .usernameParameter("username")
		 .passwordParameter("password")
		 .successHandler(handler);
		 
		 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
