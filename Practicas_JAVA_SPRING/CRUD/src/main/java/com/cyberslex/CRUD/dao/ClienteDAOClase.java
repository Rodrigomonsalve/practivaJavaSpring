package com.cyberslex.CRUD.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyberslex.CRUD.entity.Clientes;

// RESULTA ESENCIAL EN LOS DAOS UTILIZAR LA ANOTACIÓN @Transactional. Sin ella, la estructura original de cualquier operación a base datos sería a través de una estructura try-catch, y abriendo y cerrando sesión. Aquí ya no hacemos eso, lo que provoca que no se ejecute ningún commit si la consulta en concreto no se ejecutó correctamente.
// Se usan puros métodos de Hibernate, para realizar operaciones en bases de datos.
// Hay que diferenciar una clase DAO de una clase REPOSITORY. En este caso, nos encontramos ante una clase DAO, aunque lleve la anotación @Repository.
// Una clase DAO se encarga de realizar las operaciones directamente en la base de datos. Una clase Reposository contiene la lògica de negocio necesaria antes de que se puedan ejecutra los mètodos del DAO; un repositorio los invoca. Mientras tanto, una clase Service, contiene la lógica del negocio una vez ejecutados los DAO. Es una capa intermedia entre los DAO y las clases controladoras. ARQUITECTURA DOMAIN-DRIVEN-DESIGN.
// Estrictamente, una aplicación podría funcionar solamente con una clase controladora que realice queries a la base de datos y ya, y espacifique la lògica del negocio (también llamada "Lógica del dominio de la aplicación") (si ya no hay playeras, dirigir a una url determinada que diga producto agotado). Esto nos lleva a concluier, que puede haber apliciones sin alguna de las clases mencionadas, y funcionar correctamente. Sin embargo, se recomienda usar la arquitectura antes mencionada.


// HAY QUE RECORDAR QUE  EN TODA CLASE DAO PODEMOS TRABAJAR DE 4 FORMAS DIFERENTES, PERO CON EL MISMO RESULTADO, PARA CREAR LAS CONSULTAS:
// 1) Con la clase SessioFactory. Es de Hibernate.
// 2) Con la clase EntityManager. Es de JPA y es mas granular que SessionFactory
// 3) Heredando de JPARepository. Es muy facil de implementar pero no es granular. Es lo mas usado.
// 4) Usando la anotación @Query. Es especifico de Spring, y es el mas granular de todos.

// EN TODOS LOS METODOS DAO SIEMPRE SE DEBE ABRIR Y CERRAR SESION (AUNQUE NO SEA EXPRESAMENTE) Y CREAR LA CONSULTA.
@Repository
public class ClienteDAOClase implements ClienteDAO {
	
	@Autowired
	private SessionFactory sessionFactory; //SessionFactory es un bean que se crea en el contendor de beans (SPRING-CRUD-SERVLET.XML).
											//Hay que recordar que SessionFactory es una interfaz de hibernate que nos va a ayudar a realizar consultas y operaciones en una base de datos.
	
	@Override
	@Transactional
	public List<Clientes> getCliente(){
		
		Session miSession = sessionFactory.getCurrentSession(); // Hay que recordar que podemos usar también "Session miSession = sessionFactory.openSession();" Sin embargo, si estamos utilizando un framework es recomendable usar getCurrentSession, pues no vuelve a abrir otra sesión sino que usa alguna ya existete y, como vemos, no es necesario cerrar sesión.
		
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


	@Override
	@Transactional
	public void eliminarCliente(int id) {
		
		Session miSession=sessionFactory.getCurrentSession();
		
		Query consulta=miSession.createQuery("delete from Clientes where id=:IdDelCliente");
		
		consulta.setParameter("IdDelCliente", id);
		
		consulta.executeUpdate();
		
	}
	
	

}
