package com.annotations;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.*;

public class UsoAnotations2 {
	
	public static void main (String [] args) {
		
		try (
			//Las siguientes líneas, siguen la misma lógica que antes (proyecto Estudio).	
			AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(EmpleadosConfig.class)) {
			Empleados Antonio = contexto.getBean("comerciante", Empleados.class);
			// El nombre del bean "comerciante" lo podemos estar aplicando a otras clases diferentes a la actual, para que apunten a otras. Entonces, en este código no se debe cambiar nada, aunque qusiérasmos instanciar un objeto de otra clase.
			Empleados Ana = contexto.getBean("comerciante", Empleados.class);
			
			Empleados empleado = contexto.getBean("directorFinanciero", Empleados.class);
			
			System.out.println(Ana.getTipoEmpleado());
			System.out.println(empleado.getInformes());
			
			
			//Estamos imprimiendo el valor del archivo properties (Tema diferente al de inyección de dependencias e inversión de control)
			System.out.println(empleado.getCorreoElectronico());
			
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
