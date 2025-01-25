package com.cyberslex.api_soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

//1)PRIMERO PASO DE LA SOLICITUD
//POR  CADA url endpoint QUE QUIERAS CREAR, DEBES CREAR UNA CLASE COMO ESTA. LO MISMO SUCEDE CON LAS DEMAS CLASES.
// AQUI SE DEFINEN TODAS LAS OPERACIONES QUE SE PODRÁN LLEVAR A CABO EN ESTE ENDPOINT. DEFINE LA RESPUESTA. ES COMO EL CONTROLADOR.
@Endpoint
public class HelloEndpoint {
	
	private static final String NAMESPACE_URI="http://example.com/soap"; //El "namespace" no es una url real: no es accesible. Sólo tiene como finalidad identificar de forma única a los elementos de este esquema. Puede que existan elementos  con el mismo nombre aquí mismo o en otro endpoint.  -->
	
	
	//@PayloadRoot mapea las solicitudes entrantes con el namespace y el nombre del elemento. Si coinciden, invoca el método.
	@PayloadRoot(namespace=NAMESPACE_URI, localPart ="HelloRequest") //localPart es el nombre del elemento del archivo xsd al que haremos referencia.
	@ResponsePayload
	@SoapAction("http://example.com/soap/sayHello")
	public HelloResponse sayHello(@RequestPayload HelloRequest request) {
		
		HelloResponse response=new HelloResponse();
		
		response.setMessage("Hello, "+ request.getName() +"!");
		
		return response;
	}
	

}
