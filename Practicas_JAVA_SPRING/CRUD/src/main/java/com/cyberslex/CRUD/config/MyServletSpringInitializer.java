package com.cyberslex.CRUD.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;


//Hay que recordar que para el patrón de arquitectura modelo-vista-controlador se usan 2 archivos de configuración: spring-servlet.xml y web.xml
//También hay que recordar que cuando se ejecuta el proyecto, se invoca primero el web.xml y èste, a su vez, al spring-servlet.xml
//Ahora estos archivos, son sustituidos por código Java. Este es sustituye al web.xml

//En este proyecto, decidimos utilizar un web.xml en formato Java, pero derivado de que el contenedor de beans sí está en formato xml, se tuvo que heredar la clase AbstractDispatcherServletInitializer 

public class MyServletSpringInitializer extends AbstractDispatcherServletInitializer

//AbstractAnnotationConfigDispatcherServletInitializer 

{
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		
		return null;
	}

//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		// TODO Auto-generated method stub
////		return new Class[] {App.class}; //Nombre del archivo de configuración (contenedor de beans)    //Se utilizó este método cuando el contendro de beans se encontraba en formato java. En nuestro caso, no.
//		return null;
//	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};  //Ubicación de la raíz. En nuestro caso la raíz es /WEB-INF/. Aquí están ubicadas nuestras vistas.
	}
	
	@Override
	protected WebApplicationContext createServletApplicationContext() {  
		
		XmlWebApplicationContext context = new XmlWebApplicationContext();
		
		context.setConfigLocation("/WEB-INF/spring-crud-servlet.xml");
		
		return context;
	}
	
	

}
