package org.cyberslex.Api_rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//ESTE ES UN POJO (PLAIN OLD JAVA OBJECT)
//1) No extiende de otras clases.
//2) Tiene variables accesibles por mètodos getters y setters.
//3) No necesitan un entorno específico para ejecutarse.

//Es necesario un POJO para transformar el contenido de un JSON a código Java (un objeto). Esto se va a realizar desde la clase DriverJackson.


@JsonIgnoreProperties(ignoreUnknown=true) // SE USA PORQUE, EN EL PROCESO DE DESERIALIZACION PUEDE QUE HAYA VARIABLES QUE NO COINCIDAN CON LAS DEL JSON. ESAS VARIABLES SE IGNORARÍAN.
public class Empleado {
	
	private int id;
	private String nombre;
	private String apellido;
	private boolean activo;
	private String[] idiomas;
	private int edad;
	private Datos_Empleado datos_registro;
	
	public Empleado() {
		super();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo=activo;
	}

	public String[] getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String[] idiomas) {
		this.idiomas = idiomas;
	}

	public Datos_Empleado getDatos_registro() {
		return datos_registro;
	}

	public void setDatos_registro(Datos_Empleado datos_registro) {
		this.datos_registro = datos_registro;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
}
