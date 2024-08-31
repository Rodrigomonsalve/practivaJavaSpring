package org.cyberslex.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration 
@EnableWebMvc
@ComponentScan(basePackages = "org.cyberslex.security")
public class App{
	
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/");
		
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
		
	
	}
    
}
