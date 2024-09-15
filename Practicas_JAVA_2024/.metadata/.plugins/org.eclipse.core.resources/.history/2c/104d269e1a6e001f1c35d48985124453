package com.hibernate.relaciones.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="clientes1")
public class ClienteRelacionesTablas {
	
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)   // GeneratedValue significa que "id" va a generar sus valores de forma automática y secuencial (auto_increment), por lo que no tendremos que preocuparnos por llenarlo nosotros.
	private int id;										// Hay principalmete 2 formas de generar estos valores: IDENTITY y SEQUENCE. Identity simplemente el motor de la base de datos es quien las va a generar. En sequence nosotros necesitamos crear una tabla de nombre hibernate_sequence y ahí se van a ir generando los valores. Por defecto es SEQUENCE, por lo que resuta esencial colocar este atributo.  
	
	
	@Column(name="nombre")
	private String nombre;
	
	
	@Column(name="telefono")
	private String telefono;
	
	
	//Si tiene esta anotación, significa que clientes1 es la tabla hija, puesto que estamos indicando que la columna "id" de la tabla clientes1, será la llave foránea de la tabla detalles_cliente. Tenemos que replicar el mismo diagrama de la base de datos.
	@OneToOne(cascade=CascadeType.ALL)  //Cualquier cosa que le hagamos a un registro en la tabla cliente1, tendrá efectos sobre el registro correspondiente, en la tabla detalles_cliente. Sirve para realizar eliminaciones de registros de una forma más eficaz.
	@JoinColumn(name="id")              
	private DetallesCliente detallesCliente;


	
	
	
	public ClienteRelacionesTablas(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		//this.detallesCliente = detallesCliente;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}


	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}
	
	
	
	
	

}
