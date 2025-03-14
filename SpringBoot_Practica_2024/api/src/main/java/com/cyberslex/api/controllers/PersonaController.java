package com.cyberslex.api.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import com.cyberslex.api.entity.Persona;
import com.cyberslex.api.service.PersonaServiceImpl;

// LAS URLs CREADAS EN UN RESTCONTROLLER, EN LA PRÁCTICA, NO SON URLs VISIBLES PARA EL USUARIO FINAL. SON URLs DE APIS A LAS QUE ALGUNA URL VISIBLE PARA EL USUARIO FINAL, REALIZA SOLICITUDES PARA OBTENER INFORMACIÓN.
// POR LO TANTO, ES TRASCENDENTAL ACTIVAR CORS CON LA ANOTACIÓN @CrossOrigin, PUESTO QUE LAS SOLICITUDES DESDE EL FRONTEND NUNCA VAN A COINCIDIR CON LA URLs DE LAS APIS Y SE PUEDEN BLOQUEAR.
// CON LA ANOTACIÓN @CrossOrigin(origins = "*") SIGNIFICA QUE TODOS LAS URLS DEL USUARIO FINAL PUEDEN ACCEDER A LAS URLS DE ESTE CONTROLADOR.
// PUEDES ESPECIFICAR POR SEGURIDAD: @CrossOrigin(origins = "http://www.ejemplo.com")





@RestController //PROVOCA QUE LOS METODOS SIEMPRE DEVUELVAN UN JSON. POR DEFECTO ES EN JSON. SI QUIERES QUE SOPORTE XML, DEBERÁS CONFIGURAR TU MÉTODO CONTROLADOR Y AGREGAR EN EL POM LA LIBRERÍA jackson-dataformart-xml 
				// DE IGUAL FORMA, LA SOLICITUD HTTP DEL CLIENTE DEBE SOLICITAR UN JSON DENTRO DEL VALOR "ACCEPT"   */* ---> acepta todos los formatos , application/json ---> sólo acepta json , application/xml ---> sólo acepta xml
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/persona")
public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{  // Como ya sabemos, al simplemente heredar, PersonaController puede usar los métodos de la clase heredada como si fueran propios, sin necesidad de que se declaren dentro de la clase.
																						
	
//	private PersonaServiceImpl personaService;
//	
//	public PersonaController(PersonaServiceImpl personaService){
//		this.personaService = personaService;
//		
//	
//	}
	
	
	// http://localhost:8082/api/v1/persona/search?filtro=Rodrigo  ---> Se envía de esta forma al servidor. Responde con un json en el body del response http.
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String filtro){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro));
			
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
			
		}
	}
	
	@GetMapping("/searchPaged")
	public ResponseEntity<?> search(@RequestParam String filtro, Pageable pageable){
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(servicio.search(filtro, pageable));
			
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
			
		}
	}
	
	
	
	
	
	
	
//	@GetMapping("") //También se puede hacer esto. Significa que con tan solo acceder a /api/v1/persona ya estarás accediendo al método getAll().
//	public ResponseEntity<?> getAll(){     // Hay que recordar que la clase ResponseEntity sirve para poder personalizar una respuesta http. La puedes utilizar en cualquier mètodo que se encuentre dentro de un controlador. No fue indispensable usarla en este método. El primer argumento es el mensaje que verás en panatalla, y el segundo, la respuesta http que tú desees como respuesta.
//		
//		try {
//			
//			return ResponseEntity.status(HttpStatus.OK).body(personaService.findAll()); // Hay que recordar que en el código de api-rest de Pildoras Informàticas, retornamos "new ResponseEntity<> (error,HttpStatus.BAD_REQUEST);".  En el caso de este método, el método body retorna exactamente lo mismo (un objeto de tipo ResponseEntity), por lo que resulta irrelevante cuál de las 2 formas usar. El objeto "error" es a lo mismo que "personaService.findAll()". 
//			
//		}catch (Exception e){
//			
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}"); // Con esta forma, nos evitamos crear otro método con la anotación @ExceptionHandler. Retornamos un json también con el atributo "error".
//		
//		}
//		
////		try {
////			return personaService.findAll();     //Todo este bloque de código tiene el mismo comportamiento que lo anterior. Sin embargo, no es posible adecuar un resultado en formato json en caso de error.
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		}
//		
//	}
//	
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getOne(@PathVariable Long id){   // Hay que recoradar que con la anotación @PathVariable estamos creando una variable que pueda pasar como argumento en la URL (la debemos poner de forma manual). Como segundo paso esta variable la debemos pasar a la URL que nostros creemos.
//	
//		try {
//			
//			return ResponseEntity.status(HttpStatus.OK).body(personaService.findById(id));
//			
//		}catch (Exception e){
//			
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
//		
//		}
//	}
//	
//	
//	@PostMapping("/")
//	public ResponseEntity<?> save(@RequestBody Persona entity){ // La anotación sirve para tomar la información de un json y, en este caso, formar un objeto de tipo Persona. Para probar, el json lo tenemos que crear de forma manual. Para eso usamos Postman.
//		
//		try {
//			
//			return ResponseEntity.status(HttpStatus.OK).body(personaService.save(entity)); // Lo que enviemos en json, se guaradrá en la base de datos.
//			
//		}catch (Exception e){
//			
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
//		
//		}
//		
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona entity){
//		
//		try {
//			
//			return ResponseEntity.status(HttpStatus.OK).body(personaService.update(id, entity));
//			
//		}catch (Exception e){
//			
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
//		
//		}
//		
//	}
//	
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id){   // Si nos fijamos, este URL es la misma que la del mètodo update. Sin embargo, para probar de forma más efectiva, usamos Postman, puesto que ahí podemos seleccionar el método.
//		
//		try {
//			
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(personaService.delete(id));
//			
//		}catch (Exception e){
//			
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
//		
//		}
//		
//		
//	}

}
