package com.cyberslex.MVC_Maven.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Esta clase sirve para el tema de MVC Tags y para el tema de validación de formularios.

public class Alumno {
	
	@NotNull
	@Size(min=2, message="Campo requerido")
	private String nombre;
	
	private String apellido;
	
	
	@Min(value=10, message="No se permiten edades menores a 10 años")
	@Max(value=100, message="No se permiten edades superiores a 100 años")
	private int edad;
	
	@Email
	private String email;

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	

}
