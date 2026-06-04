package com.winter.react.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.web.WebProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageMappingConfig implements WebMvcConfigurer {

	@Bean
	LocaleResolver localeResolver() {

		SessionLocaleResovler resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);

		CookineLocaleResolver resolver2 = new CookieLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);

		return resolver2;

	}

	@Bean
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		return changeInterceptor;

	}

}
