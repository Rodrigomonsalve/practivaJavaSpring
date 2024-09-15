package com.cyberslex.api.controllers;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cyberslex.api.entity.Base;


// Hay que recordar que esta estructura genércia significa que todos los métodos o arrays de esta clase que utilicen java generics, al instanciar la clase, y ejecutar estos métodos, sólo podran usar como parámeto el tipo de variables que nosotros queramos, con la condición de que estas variables hereden de Base o Serializable.
// Ejemplo: BaseController<Ajedrecistas> genericsHeredando = new GenericsHeredando<Ajedrecistas>(); ---> los métodos de esta clase (GenericsHeredando) sólo podrán trabajar con clases que hereden de Jugador, en este caso, Ajedrecistas.
//GenericsHeredando<Parseos> genericsHeredando = new GenericsHeredando<Parseos>(); ---> Esto es erróneo, porque la clase Parseos no hereda de Jugador
public interface BaseController<E extends Base, ID extends Serializable> {
	
	public ResponseEntity<?> getAll();
	
	public ResponseEntity<?> getOne(@PathVariable ID id); 
	
	public ResponseEntity<?> save(@RequestBody E entity);
	
	public ResponseEntity<?> update(@PathVariable ID id, @RequestBody E entity);
	
	public ResponseEntity<?> delete(@PathVariable ID id);
	
	public ResponseEntity<?> getAll(Pageable pageable);
	
	

}
