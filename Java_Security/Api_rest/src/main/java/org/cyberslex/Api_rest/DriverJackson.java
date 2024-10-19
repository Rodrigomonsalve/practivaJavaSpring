package org.cyberslex.Api_rest;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

//EN ESTE PROYECTO NO ES NECESARIO USAR SPRING
// JACKSON SIRVE PARA SERIALIZAR Y DESERIALIZAR.
// SERIALIZAR: DE OBJETO JAVA A JSON
// DESERAILIZAR: DE JSON A OBJETO JAVA

public class DriverJackson {
	
	public static void main(String [] args) {
		
		try {
			ObjectMapper miMapper = new ObjectMapper();
			
			Empleado miEmpleado = miMapper.readValue(new File("datos-empleados.json"), Empleado.class); //El objeto miEmpleado guarda la información del json. Del json se pasa a la clase Empleados.
			
			System.out.println("Nombre del empleado:"  + miEmpleado.getNombre());
			
			System.out.println("Usuario activo:"  + miEmpleado.isActivo());
			
			for(String idioma : miEmpleado.getIdiomas()) {
			
				System.out.println("Idioma que domina el usuario:"  + idioma);
				
			}
				
				Datos_Empleado datos_empleado = miEmpleado.getDatos_registro();
				
				System.out.println("Domicilio del empeado ubicado en: "  +  datos_empleado.getDomicilio());
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

		
	}


