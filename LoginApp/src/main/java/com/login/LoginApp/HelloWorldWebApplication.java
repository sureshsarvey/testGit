package com.login.LoginApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.login")
@EnableJpaRepositories("com.login.dao")
@EntityScan(basePackages = "com.login.model")
public class HelloWorldWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldWebApplication.class, args);
	}
}
