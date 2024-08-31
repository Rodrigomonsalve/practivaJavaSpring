package com.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.relaciones.entity.ClienteRelacionesBidireccional;
import com.hibernate.relaciones.entity.ClienteRelacionesTablas;
import com.hibernate.relaciones.entity.DetallesCliente;
import com.hibernate.relaciones.entity.DetallesClienteBidireccional;

import org.hibernate.*;

//En Hibernate, a diferencia de JDBC (conexión tradicional) podemos crear un archivo de conexión a la BD mediante archivo XML (hibernate.cfg.xml)

public class GuardaClientesRelacionesBidireccional {
	
	public static void main(String [] args) {
		
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteRelacionesBidireccional.class).addAnnotatedClass(DetallesClienteBidireccional.class).buildSessionFactory();
		
		Session miSession = miFactory.openSession();
		
		try {
			
			ClienteRelacionesBidireccional cliente1 = new ClienteRelacionesBidireccional("Enrique", "556766467884");
			
			DetallesClienteBidireccional detallesCliente = new DetallesClienteBidireccional("correo@correo.com", "Cuchilla 52");
			
			
			
			cliente1.setDetallesCliente(detallesCliente);
			detallesCliente.setClienteRelacionesBidireccional(cliente1);
			
			
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

