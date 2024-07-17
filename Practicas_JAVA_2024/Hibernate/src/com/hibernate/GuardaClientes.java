package com.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

//En Hibernate, a diferencia de JDBC (conexión tradicional) podemos crear un archivo de conexión a la BD mediante archivo XML (hibernate.cfg.xml)

public class GuardaClientes {
	
	public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			Clientes cliente1 = new Clientes("Guillermo", "Muñoz", "Roma");
			
			miSession.beginTransaction();
			
			
			//MÉTODO NÚCLEO
			miSession.save(cliente1); //Si nos fijamos, ya no hay consultas SQL de forma directa. Hibernate es un ORM de alto nivel.
			
			miSession.getTransaction().commit();
			
			System.out.println("Registro insertado exitosamente en BBDD");
			
			miSession.close();
			
		}finally {
			miFactory.close();
		}
		
	}

}
