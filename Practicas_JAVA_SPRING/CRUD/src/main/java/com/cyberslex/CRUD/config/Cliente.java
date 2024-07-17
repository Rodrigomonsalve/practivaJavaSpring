package com.cyberslex.CRUD.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


//No es posible insertar valores en la base de datos si no hay una columna definida como id (identifier) desde este código. Sin embargo, en la base de datos puede que ninguna columna sea llave primaria.
// A pesar de lo anterior, si la base de datos no tiene definida una llave primaria, todos los valores que le enviemos tendrán valor de 0.

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String direccion;
	
	
//La anotación GeneratedValue es opcional, pero en la base de datos la llave primaria deberá ser de tipo autoincremental. De lo contrario mandará error.	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column
	private int id;
	
	


	public Cliente(String nombre, String apellido, String direccion) {  //Derivado de que el id es incremental de acuerdo a las líneas anteriores, ya no es necesario pasar un algún parámetro como id. En este caso sí lo hacemos, pero no es totalmente correcto.
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;

	}
	
	
	public Cliente() {
		
	}
//	@Override
//	public String toString() {
//		
//		return "Clientes[id= "+id+", nombre= "+nombre+" , apellido="+apellido+ " , direccion = " + direccion + " , +"];
//	}
	
	

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Clientes [nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", id=" + id
				+ "]";
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
	
}
