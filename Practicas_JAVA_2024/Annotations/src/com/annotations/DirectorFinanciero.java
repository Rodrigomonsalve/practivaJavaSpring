package com.annotations;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Component
public class DirectorFinanciero implements Empleados {
	
	
	// Estamos inyectando una dependencia (CreacionInforme) exactamente igual a como lo hicimos en el proyecto Estudio. Se aplica la misma lógica que en su clase DirectorEmpleado
	// Primero se tuvo que haber creado la estructura de inyección de dependencias en el archivo de configuración (EmpleadosConfig.java), con las anotaciones @Bean.
	// Hay que recordar que para que una dependencia se inyecte, primero debe ser un bean. La anotación @Bean en el archivo de configración también los convierte en bean, sin necesidad de que lleven la clase respectiva (Informe) lleva la anotación @Component
	private CreacionInformeFinanciero informeFinanciero;

	public DirectorFinanciero(CreacionInformeFinanciero informeFinanciero) {
		this.informeFinanciero = informeFinanciero;
	}
	
	
	
	//(Tema diferente a inversión de control e inyección de dependencias) En esta 2 variables estamos trayendo los valores de archivo properties.
	@Value("${email}")
	private String correoElectronico;
	
	@Value("${nombre}")
		private String nombre;
	
	
	

	@Override
	public String getTipoEmpleado() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getTareas() {
		return "Tareas de Director Financiero";
	}
	
	//En este método utilizamos la dependencia inyectada.
	@Override
	public String getInformes() {
		return informeFinanciero.getInformeFinanciero();
	}
	
	
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public String getNombre() {
		return nombre;
	}

	
}
