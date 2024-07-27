package com.cyberslex.CRUD.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.entity.Clientes;



@Repository
public class ClienteDAOClase implements ClienteDAO {
	
	@Autowired
	private SessionFactory sessionFactory; //SessionFactory es un bean que se crea en el contendor de beans (SPRING-CRUD-SERVLET.XML).
											//Hay que recordar que SessionFactory es una interfaz de hibernate que nos va a ayudar a realizar consultas y operaciones en una base de datos.
	
	@Override
	@Transactional
	public List<Clientes> getCliente(){
		
		Session miSession = sessionFactory.getCurrentSession();
		
		Query<Clientes> miQuery = miSession.createQuery("from Clientes", Clientes.class);
		
		List<Clientes> clientes = miQuery.getResultList();
		
		return clientes;
		
	}


	@Override
	@Transactional
	public void insertaCliente(Clientes elCliente) {
		
		Session miSession = sessionFactory.getCurrentSession();
		
		miSession.saveOrUpdate(elCliente);
		
	}


	@Override
	@Transactional
	public Clientes getCliente(int id) {
		
		Session miSession=sessionFactory.getCurrentSession();
		
		Clientes elCliente = miSession.get(Clientes.class, id);
		
		return elCliente;
	}
	
	

}
