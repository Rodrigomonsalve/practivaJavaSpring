package com.cyberslex.CRUD.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.config.Cliente;

@Repository
public class ClienteDAOClase implements ClienteDAO {
	
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Cliente> getCliente(){
		
		Session miSession = sessionFactory.getCurrentSession();
		
		Query<Cliente> miQuery = miSession.createQuery("from Cliente", Cliente.class);
		
		List<Cliente> clientes = miQuery.getResultList();
		
		return clientes;
	}


	@Override
	public void insertaCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}
	
	

}