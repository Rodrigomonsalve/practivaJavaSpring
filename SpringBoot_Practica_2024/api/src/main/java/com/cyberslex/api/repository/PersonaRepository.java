package com.cyberslex.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyberslex.api.entity.Persona;

// ES IMPORTANTE MENCIONAR QUE LOS 3 MÉTODOS DECLARADOS EN ESTA CLASE, SON MÉTODOS ALTERNOS A LOS ESTABLECIDOS EN LA CLASE JpaRepository. NO SON INDISPENSABLES, PORQUE YA HAY MÉTODOS EN JpaRepository CON LOS QUE PUEDES BUSCAR PERSONAS.
// ESTOS 3 MÉTODOS SIRVEN POR SI QUIERES USAR QUERIES DIRECTAMENTE. SE DECLARARON OTROS 3 CON EL OBJETO PAGE.
// LOS 6 MÉTODOS HACEN EXACTAMENTE LO MISMO.
// NO ES NECESARIO DESARROLLAR NINGUNO DE ELLOS NI AQUÍ NI EN NIGUNA OTRA CLASE. JPA YA RECONOCE SU FUNCIÓN.

@Repository
//public interface PersonaRepository extends JpaRepository<Persona, Long> {  //Long es el tipo de variable "id" creada en la clase entidad "Persona"
public interface PersonaRepository extends BaseRepository<Persona, Long>{
	
	
	// ESTE ES UN MÉTODO DERIVADO. NOS LO PROVEÉ JPAREPOSITORY. findBy ES COMO UN "SELECT",  "Nombre" ES EL NOMBRE DEL ATRIBUTO DE LA ENTIDAD Y "Containing" SIGNIFICA QUE VA A BUSCAR RESULTADOS PARCIALES: SELECT * FROM ++++ WHERE nombre LIKE %JUAN%;
	// JPA NOS DA KEYWORDS PARA SER USADOS EN EL NOMBRE DEL MÉTODO. NO PUEDES NOMBRARLO COMO TÚ QUIERAS.
	// DIGAMOS QUE LA QUERY SE ENCUENTRA EN EL NOMBRE DEL MÉTODO.
	// LAS KEYWORDS SON MUCHAS, POR LO QUE HAY QUE INVESTIGARLOS.
	List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
	
	// EL OBJETO PAGE NOS AYUDA A CONTENER UNA LSTA DE ELEMENTOS, AL IGUAL QUE LIST, PERO NOS AYUDA A DIVIDIR O PAGINAR LA LISTA Y DECIDIR CUÁNTOS ELEMENTOS DEBE CONTENER CADA PÁGINA.
	Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable);
	
	
	//boolean existsByDni(int dni);
	
	
	 
	// SE USA JPQL (Java Persistence Query Language). SE DEBEN USAR LOS NOMBRES DE LAS ENTIDADES(CLASES), NO EL DE LAS TABLAS
	// LOS 2 PUNTOS EN :filtro SIGNIFCA QUE AHÍ SE VA A GUARDAR UN VALOR QUE VIENE DE LOS PARÁMETROS AL INVOCARSE EL MÉTODO.
	@Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
	List<Persona> search(@Param("filtro") String filtro);
	
	@Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
	Page<Persona> search(@Param("filtro") String filtro, Pageable pageable);
	
	
	
	// NO FUNCIONA
	// SE USA SQL.
	@Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE '%:filtro%' OR persona.apellido LIKE '%:filtro%'", nativeQuery= true)
	List<Persona> searchNativo(@Param("filtro") String filtro);
	
	@Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE '%:filtro%' OR persona.apellido LIKE '%:filtro%'", countQuery = "SELECT count(*) FROM persona", nativeQuery= true)
	Page<Persona> searchNativo(@Param("filtro") String filtro, Pageable pageable);

}
