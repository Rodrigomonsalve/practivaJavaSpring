package com.cybserlex.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cyberslex.model.Cliente;
import com.cyberslex.model.JPAUTIL;


//No hacemos uso de la anotacion @Repository, porque no estamos usando Spring.

//EN TODA CLASE DAO PODEMOS TRABAJAR DE 4 FORMAS DIFERENTES, PERO CON EL MISMO RESULTADO, PARA CREAR LAS CONSULTAS:
//1) Con la clase SessioFactory. Es de Hibernate.
//2) Con la clase EntityManager. Es de JPA y es mas granular que SessionFactory
//3) Heredando de JPARepository. Es muy facil de implementar pero no es granular. Es lo mas usado.
//4) Usando la anotación @Query. Es especifico de Spring, y es el mas granular de todos.

//EN TODOS LOS METODOS DAO SIEMPRE SE DEBE ABRIR Y CERRAR SESION (AUNQUE NO SEA EXPRESAMENTE) Y CREAR LA CONSULTA.

public class ClienteDAO {
	
	EntityManager entity=JPAUTIL.getEntityManagerFactory().createEntityManager();
	
	public void guardar(Cliente cliente) {
		
		entity.getTransaction().begin();
		
		entity.persist(cliente);
		
		entity.getTransaction().commit();
		
		JPAUTIL.shutdow();
		
	}
	
	public void editar(Cliente cliente) {
		
		entity.getTransaction().begin();;
		
		entity.merge(cliente);
		
		entity.getTransaction().commit();
		
		//JPAUTIL.shutdow();
		
	}
	
	public Cliente buscar(Long id) {
		
		Cliente c = new Cliente();
		
		c=entity.find(Cliente.class, id);
		
		//JPAUTIL.shutdow();
		
		return c;
		
	}
	
	public void eliminar(Long id) {
		Cliente c=new Cliente();
		
		c=entity.find(Cliente.class, id);
		
		entity.getTransaction().begin();
		
		entity.remove(c);
		
		
	}
	
	
	public List<Cliente> obtenerClientes() {
		
		List<Cliente> listaClientes = new ArrayList<>();
		
		Query q = entity.createQuery("SELECT c FROM Cliente c");
		
		listaClientes=q.getResultList();
		
		//JPAUTIL.shutdow();
		
		return listaClientes;
		
	}
	
	

}
