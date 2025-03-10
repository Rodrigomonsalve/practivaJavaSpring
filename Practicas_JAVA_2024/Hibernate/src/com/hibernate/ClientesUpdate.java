package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// ELIMINA REGISTROS

public class ClientesUpdate {
	
	
public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			miSession.beginTransaction();
			
//			int clienteId = 5;
//			
//			Clientes miCliente = miSession.get(Clientes.class, clienteId);
//			
//			miCliente.setNombre("Juan");
			
			
			//MÉTODO NÚCLEO
			miSession.createQuery("delete Clientes where Nombre ='Rodrigo'").executeUpdate();
			
			miSession.getTransaction().commit();
			
			System.out.println("Registro eliminado");

}finally {
	
	miFactory.close();
}
		
}

}
