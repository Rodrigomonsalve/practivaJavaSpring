package com.cyberslex.MVC_Maven.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


//Hay que recordar que para el patrón de arquitectura modelo-vista-controlador se usan 2 archivos de configuración: spring-servlet.xml y web.xml
//También hay que recordar que cuando se ejecuta el proyecto, se invoca primero el web.xml y èste, a su vez, al spring-servlet.xml
//Ahora estos archivos, son sustituidos por código Java. Este es sustituye al web.xml

public class MyServletSpringInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {App.class}; //Nombre del archivo de configuración.
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};  //Ubicación de la raíz. En nuestro caso la raíz es /WEB-INF/. Aquí están ubicadas nuestras vistas.
	}
	
	

}
