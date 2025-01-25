package com.cyberslex.api_soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


//AUNQUE TENGAS VARIOS ENDPOINTS, SÓLO NECESITAS UNA SÓLO CLASE CON LA ANOTACIÓN @EnableWs. SÓLO UN ARCHIVO DE CONFIGURACIÓN, AUNQUE SÍ PUEDES CREAR DIVEROS METODOS COMO messageDispatcherServlet() DENTRO DE ELLA.
@Configuration
@EnableWs
public class ServerSoap {
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context){
		
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<>(servlet,"/ws/*");  //SI SOLO NECESITAS ACCEDER A MULTIPLES ENDPOINTS BAJO EL CONTEXTO /ws, SÓLO DEBES CREAR UN SOLO METODO.
	}
	
	

}
