package com.indianbank.cbs.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.indianbank.cbs.interceptor.IndianBankInterceptor;

@Configuration
@ImportResource(locations = {"classpath:database.xml"})
@EnableWebMvc
public class IndianBankConfiguration implements WebMvcConfigurer {
	  @Bean("messageSource")
		public MessageSource messageSource() {
			ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
			messageSource.setBasenames("classpath:MessageResources", "classpath:IndianBankConfiguration", "classpath:QueryConstants");
			messageSource.setCacheSeconds(10);
			return messageSource;
		}
	  public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new IndianBankInterceptor()).addPathPatterns("/**");
	 }
		@Override
	 	public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**");
	    }

}
