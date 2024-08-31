package com.cyberslex.API_REST_COMPLETO.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

//ESTE PROYECTO ES MUY BUENO PARA RECORDAR EXCEPCIONES, SOBRE TODO EXCEPCIONES MANUALES.
// En nuestro caso, crear excepciones personalizadas, sirve para evitar que se muestre el mensaje por default del servidor en pantalla, por estética y por seguridad (El recurso no existe)

//El primero paso para crear excepciones personalizadas es crear una clase que herede de Exception o RuntimeException.
// El segundo paso es crear un objeto de tipo de la clase que hereda de RuntimeException o Exception dentro de una condicional o un try catch. Esto se puede hacer en esta misma clase o en otra. En nuestro caso lo hicimos desde el controlador.
// El tercer paso es mandar a llamar un mètodo mediante la anotación @ExceptionHandler en el controlador. Esto sólo funciona para controladores. En nuestro caso va a devolver un json con campos de error que nosotros creamos en otra entidad.
public class EmpleadoException extends RuntimeException {

	public EmpleadoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EmpleadoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EmpleadoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
