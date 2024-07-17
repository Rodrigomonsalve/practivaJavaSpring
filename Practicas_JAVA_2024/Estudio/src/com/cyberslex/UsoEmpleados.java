package com.cyberslex;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsoEmpleados {
	
	public static void main (String [] args) {
		ClassPathXmlApplicationContext contexto = new ClassPathXmlApplicationContext("applicationContext.xml");
		 
		Empleados Juan = contexto.getBean("miEmpleado", Empleados.class);
		Empleados Erika = contexto.getBean("miEmpleado", Empleados.class);
		
		Informe Juanito = new Informe();
		Informe Erikita = new Informe();
		
		System.out.println(Juan.getTareas());
		
		System.out.println(Juan.getInforme());
		
		System.out.println(Juan);
		System.out.println(Erika); //Imprimiendo esto, vemos que, en Spring, los objetos creados desde un contenedor de beans apuntan al mismo steak, es decir, al mismo espacio de  memoria. Esto es un patrón de disño Singleton, lo que no ocurre con Java originalmente.
		
		System.out.println(Juanito);
		System.out.println(Erikita); //Esto es Java original. Este patrón de diseño es Prototype.
		
		contexto.close();
	}

}
