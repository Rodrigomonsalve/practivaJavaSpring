package com.hibernate;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


//EN UN MVC ESTAS OPERACIONES SE ENCUENTRAN DIVIDIDAS ENTRE CONTROLADOR Y CLASES DAO. 

public class CientesConsulta {
	
public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			miSession.beginTransaction();
			
			
			//MÉTODO NÚCLEO
			List<Clientes> losClientes = miSession.createQuery("from Clientes").getResultList();  //Se utiliza lenguaje HQL, cuando no se pueda ejecutar un mètodo determinado de Hibernate.
			
			for(Clientes unCliente : losClientes) {
				
				System.out.println(unCliente);
				System.out.println("Te traje todos los clientes");  //Imprime todo el contendio de la tabla. En un CRUD ya no será necesario hacer esto, puesto que esto se hace en la vista.
			}
			
			
			//MÉTODO NÚCLEO
			losClientes = miSession.createQuery("from Clientes cl where cl.apellido = 'Monsalve'").getResultList(); //Imprime sólo los registros en donde el apellido sea Monsalve.
			
			for(Clientes unCliente : losClientes) {
				
				System.out.println(unCliente);
	
			}
			
			miSession.getTransaction();

}finally {
	
	miFactory.close();
}
		
}
}
		
