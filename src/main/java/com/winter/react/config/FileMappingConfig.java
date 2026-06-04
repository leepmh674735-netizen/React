package com.winter.react.config;

import java.nio.file.Path;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class FileMappingConfig implements WebMvcConfigurer {

	@Value("${app.upload.url}")
	private String url;

	@Value("${app.upload.path}")
	private String path;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(url)
		.addResoureLocations(path);

	}

}
