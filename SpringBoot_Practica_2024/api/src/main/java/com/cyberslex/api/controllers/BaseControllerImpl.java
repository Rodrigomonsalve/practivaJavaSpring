package com.cyberslex.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cyberslex.api.entity.Autor;
import com.cyberslex.api.entity.Base;
import com.cyberslex.api.entity.Persona;
import com.cyberslex.api.service.AutorServiceImpl;
import com.cyberslex.api.service.BaseServiceImpl;


// Esta forma de usar clases genéricas, difiere un poco del uso normal de Java, pues, nunca vamos a instanciar de 
// de forma expresa BaseControllerImpl y, en general, clases controladoras. En Java normal sería de la siguiente forma:
// BaseControllerImpl<Persona, PersonaServiceImpl> objeto = new BaseControllerImpl<Persona, PersonaServiceImpl>();
// Luego entonces, no vamos a tener un objeto sobre el cual utilizar sus métodos. Sería, en Java normal, de la siguiente forma:
// objeto.save(Persona);
// La forma en la que funciona es que, al invocarse una URL, se ejecuta la clase controladora, por ejemplo, PersonaController. Ahí se hereda
// de esta clase y ahí YA SE ESPECIFICAN LOS TIPOS DE OBJETOS QUE SE VAN A USAR, que son "Persona" y "PersonaServiceImpl". Luego,
// ya se ejecuta el método correspondiente quien utilizará de forma automática esos objetos para funcionar. Ya no es necesario pasarlos de forma expresa: objeto.save(Persona);

// Orden de ejecución:
// Se visita una url de persona: PERSONACONTROLLER --> BASECONTROLLERIMPL  --> PERSONASERVICEIMPL --> BASESERVICEIMPL --> BASEREPOSIOTORY
// La url reconoce que debe ejecutar PERSONACONTROLLER, porque la url es específica de esa clase. Una vez ahí, debido a que hereda de BASECONTROLLER, ejecuta su método
// correspondiente, pero ya con los parámetros genéricos con el objeto Persona y PersonaServiceImpl. Se ejecuta el método respectivo, el cual, a su vez, invoca un método de PERSONASERVICEIMPL quien, a su vez, hereda de 
// BASESERVICEIMPL. PERSONASERVICEIMPL pasa a BASESERVICEIMPL un objeto de tipo PersonaRepository quien a su vez hereda de BASEREPOSITORY, para que ejecute el método correspondiente.


public abstract class BaseControllerImpl<E extends Base, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long>{

	@Autowired
	protected S servicio;
	
	@GetMapping("") //También se puede hacer esto. Significa que con tan solo acceder a /api/v1/persona ya estarás accediendo al método getAll().
	public ResponseEntity<?> getAll(){     // Hay que recordar que la clase ResponseEntity sirve para poder personalizar una respuesta http. La puedes utilizar en cualquier mètodo que se encuentre dentro de un controlador. No fue indispensable usarla en este método. El primer argumento es el mensaje que verás en panatalla, y el segundo, la respuesta http que tú desees como respuesta.
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll()); // Hay que recordar que en el código de api-rest de Pildoras Informàticas, retornamos "new ResponseEntity<> (error,HttpStatus.BAD_REQUEST);".  En el caso de este método, el método body retorna exactamente lo mismo (un objeto de tipo ResponseEntity), por lo que resulta irrelevante cuál de las 2 formas usar. El objeto "error" es a lo mismo que "personaService.findAll()". 
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}"); // Con esta forma, nos evitamos crear otro método con la anotación @ExceptionHandler. Retornamos un json también con el atributo "error".
		
		}
		
//		try {
//			return personaService.findAll();     //Todo este bloque de código tiene el mismo comportamiento que lo anterior. Sin embargo, no es posible adecuar un resultado en formato json en caso de error.
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
		
	}
	
	
	
	// http://localhost:8082/api/v1/persona/1   ---> AQUÍ LA INFORMACIÓN SE ENVÍA POR LA URL Y RESPONDE CON UN JSON EN EL BODY DEL RESPONSE HTTP.
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id){   // Hay que recoradar que con la anotación @PathVariable estamos creando una variable que pueda pasar como argumento en la URL (la debemos poner de forma manual). Como segundo paso esta variable la debemos pasar a la URL que nostros creemos.
	
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findById(id));
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		
		}
	}
	
	
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody E entity){ // La anotación sirve para tomar la información de un json que enviamos en el body del request http. En este caso, sirve para formar un objeto de tipo Persona. Para probar, el json lo tenemos que crear de forma manual. Para eso usamos Postman. Se envía hacia la base de datos en el body del request http, y nos responde con un reponse http en el body, igual en json. No se manda a través de la URL nada.
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.save(entity)); // Lo que enviemos en json, se guaradrá en la base de datos.
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody E entity){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.update(id, entity));
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){   // Si nos fijamos, este URL es la misma que la del mètodo update. Sin embargo, para probar de forma más efectiva, usamos Postman, puesto que ahí podemos seleccionar el método.
		
		try {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.delete(id));
			
		}catch (Exception e){
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
		
		}
		
		
	}
	
	
	@GetMapping("/paged")
	public ResponseEntity<?> getAll(Pageable pageable){
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll(pageable));
			
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
			
		}
	}
}
