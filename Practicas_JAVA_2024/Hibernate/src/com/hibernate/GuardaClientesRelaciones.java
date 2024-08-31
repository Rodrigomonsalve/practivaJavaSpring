package com.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.relaciones.entity.ClienteRelacionesTablas;
import com.hibernate.relaciones.entity.DetallesCliente;

import org.hibernate.*;

//En Hibernate, a diferencia de JDBC (conexión tradicional) podemos crear un archivo de conexión a la BD mediante archivo XML (hibernate.cfg.xml)

public class GuardaClientesRelaciones {
	
	public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteRelacionesTablas.class).addAnnotatedClass(DetallesCliente.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			ClienteRelacionesTablas cliente1 = new ClienteRelacionesTablas("Guillermo", "556766467884");
			
			DetallesCliente detallesCliente = new DetallesCliente("correo@correo.com", "Coapa 52");
			
			cliente1.setDetallesCliente(detallesCliente);
			
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
