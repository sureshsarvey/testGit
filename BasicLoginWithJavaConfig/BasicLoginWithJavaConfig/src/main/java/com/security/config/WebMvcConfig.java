package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Import({SecurityConfig.class,CustomSuccessHandler.class})
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.security" }, excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
public class WebMvcConfig extends WebMvcConfigurationSupport
{
	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver internalResourceViewResolver=
				new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setViewClass(JstlView.class);
		return internalResourceViewResolver;
		
	}
	
}
