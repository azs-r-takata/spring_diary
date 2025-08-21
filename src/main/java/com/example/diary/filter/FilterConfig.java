package com.example.diary.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Bean
	FilterRegistrationBean<GreetingMessageFilter> registerCorrectFilter() {
		FilterRegistrationBean<GreetingMessageFilter> regBean = new FilterRegistrationBean<>();
		regBean.setFilter(new GreetingMessageFilter());
		regBean.addUrlPatterns("/*");
		return regBean;
	}
}
