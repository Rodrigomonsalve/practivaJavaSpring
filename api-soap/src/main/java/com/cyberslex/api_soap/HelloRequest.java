package com.cyberslex.api_soap;


//ESTA CLASE JUNTO CON HelloResponse NO SE DEBEN CREAR MAUALMENTE. SE CREAN CON EL PLUGIN MENCIONADO EN EL POM. ESTO ESTA MAL

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlType;

import jakarta.xml.bind.annotation.XmlAccessType;

//2) SEGUNDO PUNTO DE PARADA DE LA SOLICITUD. EL OBJETO SE DESERIALIZA PARA PASAR AL METODO sayHello
//ESTAS SON COMO LAS ENTIDADES,PERO NORMALMENTE NO SE MAPEAN A BASE DE DATOS DESDE AQUÍ.
//LA ARQITECTURA API-SOAP NO ES IDEAL PARA PERSISTIR DATOS EN UNA BASE DE DATOS, SINO SÓLO PARA CONSULTA E INTERCAMBIO.
@XmlRootElement(namespace="http://example.com/soap") //LA ANOTACION @XmlRootElement ESTABLECE QUE ESTA CLASE DEBERÁ CONVERITR A XML Y VICEVERSA. EL ATRIBUTO namespace SÓLO AYUDA A ESPECIFICAR A QUÉ namespace PERTENECE. NO ES OBLIGATORIO.
@XmlType(name = "", propOrder = {//@XmlType SE USA PARA CONTROLAR EL ORDEN EN QUE DEBERAN APARECER LOS ELEMENTOS
	    "name",
	    "lastName",
	    "id"
	})
//@XmlAccessorType(XmlAccesType.PROPERTY)  //@XmlAccessorType DEFINE LA FORMA DE ACCESO A LOS ATRIBUTOS DE UNA CLASE. (XmlAccesType.FIELD->	opcion predeterminada. Se accederán a traves de sus atributos. @XmlElement debe ir sobre los atributos; XmlAccesType.PROPERTY->Se accederá a través de sus metodos getters. @XmlElement debe ir sobre los metodos getters) 
public class HelloRequest {
	
	private String name;
	private String lastName;
	private int id;

	@XmlElement(namespace="http://example.com/soap")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@XmlElement(namespace="http://example.com/soap")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(namespace="http://example.com/soap")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
