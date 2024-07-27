package com.cyberslex.CRUD.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.entity.Clientes;

//Un DAO son las clases que pertenecen a la capa de acceso a datos. Es en donde se encuentran las operaciones de consulta, inserción, modificació o elimincación de datos de una base de datos.

@Repository
public interface ClienteDAO {
	
	public List<Clientes> getCliente();
	
	public void insertaCliente(Clientes cliente);
	
	public Clientes getCliente(int id);
	
	
	
	

}
