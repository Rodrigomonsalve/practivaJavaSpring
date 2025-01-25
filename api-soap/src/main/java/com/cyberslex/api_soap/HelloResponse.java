package com.cyberslex.api_soap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


//ESTAS SON COMO LAS ENTIDADES,PERO NORMALMENTE NO SE MAPEAN A BASE DE DATOS DESDE AQUÍ.
//LA ARQITECTURA API-SOAP NO ES IDEAL PARA PERSISTIR DATOS EN UNA BASE DE DATOS, SINO SÓLO PARA CONSULTA E INTERCAMBIO.
@XmlRootElement(namespace="http://example.com/soap")
public class HelloResponse {

	private String message;

	
	@XmlElement(namespace="http://example.com/soap")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
