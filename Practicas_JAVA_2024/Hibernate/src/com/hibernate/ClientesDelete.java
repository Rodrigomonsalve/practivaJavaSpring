package com.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// MODIFICA DATOS

public class ClientesDelete {
	
public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			miSession.beginTransaction();
			
			int clienteId = 5;
			
			Clientes miCliente = miSession.get(Clientes.class, clienteId);
			
			
			//MÉTODO NÚCLEO
			miCliente.setNombre("Juan");
			
			miSession.getTransaction().commit();
			
			System.out.println("Registro actualizado");

}finally {
	
	miFactory.close();
}
		
}

}
