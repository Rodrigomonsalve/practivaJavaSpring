package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientesLeer {
	
public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			Clientes cliente1 = new Clientes("Rodrigo", "Monsalve", "Tepetlapa");
			
			miSession.beginTransaction();
			
//			miSession.save(cliente1);
//			
//			miSession.getTransaction().commit();
//			
//			System.out.println("Registro insertado exitosamente en BBDD");
			
			System.out.println("Lectura del registro con id: " + cliente1.getId());
			
			Clientes clienteInsertado = miSession.get(Clientes.class, cliente1.getId());
			
			System.out.println("Registro: " + clienteInsertado);
			
			miSession.getTransaction().commit();
			
			System.out.println("Terminado");
	
			
			miSession.close();
			
		}finally {
			miFactory.close();
		}
		
	}

}
