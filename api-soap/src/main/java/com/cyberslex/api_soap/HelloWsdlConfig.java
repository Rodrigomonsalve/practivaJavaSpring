package com.cyberslex.api_soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


//0)  NO ES NINGUN PASO EN LA SOLICITUD. SÓLO AYUDA A CREAR LOS ENDPOINTS Y EL ARCHIVO DWSDL.
//ESTA CLASE AYUDA A CREAR EL endpoint(http://localhost:8080/ws/) Y LA RUTA EN LA QUE ESTARÁ DISPONIBLE EL ARCHIVO .wsdl(http://localhost:8080/ws/hello.wsdl).
@Configuration
public class HelloWsdlConfig {
	
	
	@Bean(name="hello") //AYUDA A NOMBRAR AL ARCHIVO WSDL(hello.wsdl)
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema helloSchema) {  //DADO QUE EL NOMBRE DEL BEAN QUE NOS INTERESA ES helloSchema, SE INYECTA A TRAVÉS DE LOS PARAMETROS COMO helloSchema
		
		DefaultWsdl11Definition definition=new DefaultWsdl11Definition();
		
		definition.setPortTypeName("HelloPort");
		definition.setLocationUri("/ws");   //SE CREA EL ENDPOINT http://localhost:8080/ws/.  EN EL ARCHIVO WSDL APARECE EN <soap:address location="http://localhost:8080/ws"/>
		//definition.setLocationUri("/ws/customer");
		
		definition.setTargetNamespace("http://example.com/soap");//El "namespace" no es una url real: no es accesible. Sólo tiene como finalidad identificar de forma única a los elementos de este esquema. Puede que existan elementos  con el mismo nombre. 
		definition.setSchema(helloSchema); //ASOCIA EL ARCHIVO hello.xsd CON ESTE METODO
		
		return definition;
		
	}
	
	@Bean
	public XsdSchema helloSchema() {
		return new SimpleXsdSchema(new ClassPathResource("hello.xsd")); //VINCULA EL ARCHIVO hello.xsd QUE SERÁ INYECTADO AL METODO defaultWsdl11Definition, A TRAVES DE SUS PARAMETROS.
	}
	
	
	//EJEMPLO CUANDO SON VARIOS endpoints:
	
	//ENDPOINT http://localhost:8080/ws/customers
	/*@Bean(name="customers") 
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customersSchema) {
		
		DefaultWsdl11Definition definition=new DefaultWsdl11Definition();
		
		definition.setPortTypeName("CustomersPort");
		definition.setLocationUri("/ws/customers");  
		
		definition.setTargetNamespace("http://example.com/customers");
		definition.setSchema(customersSchema);
		
		return definition;
		
	}
	
	@Bean
	public XsdSchema customersSchema() {
		return new SimpleXsdSchema(new ClassPathResource("customers.xsd")); //VINCULA EL ARCHIVO hello.xsd CON 
	}
	
	_______________________________________________________________________________________________________________________
	
	//ENDPOINT http://localhost:8080/ws/orders
	@Bean(name="orders") 
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema orderSchema) {
		
		DefaultWsdl11Definition definition=new DefaultWsdl11Definition();
		
		definition.setPortTypeName("OrdersPort");
		definition.setLocationUri("/ws/orders");  
		
		definition.setTargetNamespace("http://example.com/orders");
		definition.setSchema(orderSchema);
		
		return definition;
		
	}
	
	@Bean
	public XsdSchema orderSchema() {
		return new SimpleXsdSchema(new ClassPathResource("orders.xsd"));
	
	
	*/

}
