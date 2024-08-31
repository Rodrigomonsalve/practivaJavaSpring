package com.cyberslex.API_REST_COMPLETO.controller;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberslex.API_REST_COMPLETO.entity.Empleado;

@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	private List<Empleado> losEmpleados;
	
	
	
	@PostConstruct  //Esta anotación sirve para que siempre que se cargue la aplicación, se ejecute este método aunque no sea visible (segundo plano). Una vez que se ejecute getEmpleados solo obtendrá la información que ya se obtuvo, patra hacerla visible. Sirve para ahorrar recursos.
	public void cargaDatos() {  //En realidad todo podría estar junto en un sólo método.
		
		losEmpleados=new ArrayList<>();   //Hay que recordar que las lista, antes de manipularlas, se deben inicializar.
		losEmpleados.add(new Empleado("Juan", "Diaz"));
		losEmpleados.add(new Empleado("Martha", "Gonzalez"));
		losEmpleados.add(new Empleado("Carlos", "Mendez"));
		losEmpleados.add(new Empleado("Fede", "Mendiola"));
		
	}
	
	
	@GetMapping("/empleados")   //En este caso estamos utilizando la anotación @RestController y no @Controller, lo que implica que el método respectivo, en este caso, getEmpleado ya no busca una vista (jsp), sino que devuelve estrictamente lo que tú devuelvas en el método. Si el método devuelve un string, mostrará la leyenda en el navegador. 
	public List<Empleado> getEmpleado(){  //En nuestro caso estamos devolviendo una lista. Esta lista es visible, con ayuda de jackson-databind, en formata json en el navegador.
		

		return losEmpleados;
		
	}
	
	@GetMapping("/empleado/{empleadoId}") 
	public Empleado getUnEmpleado(@PathVariable String empleadoId) { //Con la anotación @PathVariable estamos creando una variable que pueda pasar como argumento en la URL (la debemos poner de forma manual). Como segundo paso esta variable la debemos pasar a la URL que nostros creemos.
		
		int id;
		
		try {
			
			id=Integer.parseInt(empleadoId);
			
		}catch(NumberFormatException e) {
			
			throw new EmpleadoException("El id " + empleadoId + " no existe. Debe ser un número");
		}
		
		
		if((id>=losEmpleados.size()) || id <0) {
			
			throw new EmpleadoException("El id " + id + " no existe.");
			
		}
		
		return losEmpleados.get(id);  //Como tercer paso debemos pasar como argumento que haya ingresado por la URL, al tradicional método get que se aplica a una lista. Hay que recordar que sirve para obtner la información de un elemento de una lista por su posición. 
		
	}
	
	@ExceptionHandler    //La anotación @ExceptionHandler se utiliza en las clases controladoras para manejar excepciones específicas. Es parte del manejo de excepciones en Spring MVC. Permite definir mètodos que se ejcutarán en cuanto se presente una excepción. Esto no ocurre en Java tradicional.
	public ResponseEntity<EmpleadoRespuestaError> manejoExcepcion(EmpleadoException ex){  //En nuestro caso, al presentarse una excepción, se forma un nuevo json con 3 atributos. 
		
		EmpleadoRespuestaError error = new EmpleadoRespuestaError();  //Sin este método sí se muestran en el navegador los mensajes de error del anterior método, pero de forma muy sucia y con màs información de la que se debería ver.
		
		error.setEstado(HttpStatus.NOT_FOUND.value());
		error.setMensaje(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		
		return new ResponseEntity<> (error,HttpStatus.BAD_REQUEST); //La clase ResponseEntity sirve para poder personalizar una respuesta http. La puedes utilizar en cualquier mètodo que se encuentre dentro de un controlador, incluso en cualquiera de los que estàn en esta clase. No fue indispensable usarla en este mètodo. Pudimos haber regresado sólo un objeto tipo EmpleadoRespuestaError. El primer argumento es el mensaje que verás en panatalla, y el segundo, la respuesta http que tú desees como respuesta.
	}
	
	

}
