package com.cyberslex.MVC_Maven.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// Hay que recordar que para el patrón de arquitectura modelo-vista-controlador se usan 2 archivos de configuración: spring-servlet.xml y web.xml
// También hay que recordar que cuando se ejecuta el proyecto, se invoca primero el web.xml y éste, a su vez, al spring-servlet.xml
// Ahora estos archivos, son sustituidos por código Java. Este es sustituye al spring-servlet.xml


@Configuration //Hay que recordar que la anotación @Configuration sirve para dejar de usar el archivo de configuración de Spring (contenedor de beans)
@EnableWebMvc 
@ComponentScan(basePackages="com.cyberslex.MVC_Maven") //Hay que recordar que, derivado de que se usa la anotación @Configuration, este archivo es un contenedor de beans, por lo que necesitamos decirle en qué paquete los debe buscar.
public class App {
	
	
   //Misma lógica que el archivo spring-servlet.xml
	@Bean
   public ViewResolver viewResolver() {
	   
	   InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	   
	   viewResolver.setPrefix("/WEB-INF/");  //En esta ruta están ubicadas las vistas.
	   viewResolver.setSuffix(".jsp");		// Este es el formato que deben tener las vistas.
	   
	   return viewResolver;
   }
}
