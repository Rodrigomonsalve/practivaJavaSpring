package com.annotations;

import org.springframework.stereotype.Component;

@Component
public interface Empleados {
	
	
	public String getTipoEmpleado();
	
	public String getTareas();
	
	public String getInformes();
	
	public String getCorreoElectronico();

}