package com.annotations;

import org.springframework.stereotype.*;
//import com.annotations.Empleados;


//Este es un bean derivado de que tiene la anotación @Component. El nombre del bean es "comerciante" y, lógicamente,hace referencia a la clase Comerciante. Tal y como si estuviéramos en un archivo de configuración xml, que ya no esatmos usando.
// La inversión de control ahora consiste en que podemos llamar a cualquier otra clase que implemente a interfaz Empleados también "comerciante" y a esta clase modificarle el nombre del bean. Entonces todos las instancias que hagan referencia a "comerciante" apuntarán a otra clase y serán instancias de la otra.

@Component("comerciante")
public class Comerciante implements Empleados{
	
	@Override
	public String getTipoEmpleado() {
		return "Soy Comerciante";
	}

	@Override
	public String getTareas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInformes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCorreoElectronico() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
