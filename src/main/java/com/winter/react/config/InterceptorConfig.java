package com.winter.react.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.react.interceptors.LoginCheckInterceptor;
import com.winter.react.interceptors.WriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private LoginCheckInterceptor loginCheckInterceptor;

	@Autowired
	private WriterCheckInterceptor writerCheckInterceptor;

	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(loginCheckInterceptor)
				.addPathPatterns("/member/*", "/qna/*")
				.excludePathPatterns("/member/login", "/member/join", "/qna/list");

		registry.addInterceptor(writerCheckInterceptor)
				.addPathPatterns("/notice/update", "/qna/update");

		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");
	}
}