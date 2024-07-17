package com.cyberslex.CRUD.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.config.Cliente;

@Repository
public interface ClienteDAO {
	
	public List<Cliente> getCliente();
	
	public void insertaCliente(Cliente cliente);
	
	
	
	

}
