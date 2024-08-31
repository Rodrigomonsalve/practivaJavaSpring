package com.hibernate.relaciones.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalles_cliente")
public class DetallesCliente {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // GeneratedValue significa que "id" va a generar sus valores de forma automática y secuencial (auto_increment), por lo que no tendremos que preocuparnos por llenarlo nosotros.
	@Column(name="id")								  // Hay principalmete 2 formas de generar estos valores: IDENTITY y SEQUENCE. Identity simplemente el motor de la base de datos es quien las va a generar. En sequence nosotros necesitamos crear una tabla de nombre hibernate_sequence y ahí se van a ir generando los valores. Por defecto es SEQUENCE, por lo que resuta esencial colocar este atributo.
	private int id;
	
	@Column(name="mail")
	private String mail;
	
	
	@Column(name="direccion")
	private String direccion;
	
	
	


	public DetallesCliente(String mail, String direccion) {
		super();
		this.mail = mail;
		this.direccion = direccion;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	

}
