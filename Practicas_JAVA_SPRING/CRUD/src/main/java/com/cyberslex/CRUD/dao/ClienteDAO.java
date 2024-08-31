package com.cyberslex.CRUD.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.entity.Clientes;


import java.io.Serializable;
import java.util.List;



//Un DAO son las clases que pertenecen a la capa de acceso a datos. Es en donde se encuentran las operaciones de consulta, inserción, modificació o elimincación de datos de una base de datos.
// En esta interfaz a veces suele heredarse de la clase JpaRepository y ya no sería necesario ni siquiera declarar los mètodos. En este caso podemos trabajar como se hace a continuación, sin ningun problema:


@Repository //La anotación @Repository funciona igual que @Component pero para trabajar con CRUDS.
public interface ClienteDAO{
	
	public List<Clientes> getCliente();
	
	public void insertaCliente(Clientes cliente);
	
	public Clientes getCliente(int id);

	public void eliminarCliente(int id);
	
	
	
	

}
