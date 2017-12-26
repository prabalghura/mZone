package com.mzone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mzone.utils.LoginHandlerInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired 
	LoginHandlerInterceptor loginHandlerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginHandlerInterceptor);
	}
}
