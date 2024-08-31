package com.cyberslex.MVC_Maven.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Resulta trascendental observar que en ningún momento, para ejecutar los métodos de una clase controladora, necesitamos instanciarla ni ejecutar los métodos como normalemnet lo haríamos.
// Basta con invocar la ruta correspondiente a cada método.
// Esto significa que todos los objetos declarados dentro de los parametros de los mètodos, se crean de forma automática cuando se invoca el método.

@Controller
@RequestMapping("/") //Esta anotación no es indispensable en este lugar. Así como está, es el valor por defecto. Simpre el ejecutar el proyecto por primera vez, va a buscar / Sin embargo, su utilidad radica en resolver conflictos de rutas, es decir, que haya otro controlador que tenga las mismas rutas que éste. En este caso, de debería crear otra ruta padre con otro nombre en este lugar.
public class ControladorMyLogin {   //La raíz es: /nombreproyecto/  La última diagonal es la diagonal indicada en el RequestMapping
	
	@GetMapping("/")   // Es la forma de hacer aparecer una página, como la de inicio, es decir, con sólo ejecutar el código del proyecto.
	public String muestraLoginPropio() {  //Sin embargo, no es necesario crear un método controlador para una página de inicio, pues esta se podría ejecutar automáticamente si creamos un index.jsp dentro de la carpeta webapp.
		
		return "loginPropio";
	}
	
	
	
	//Este método es una especie de puente que sirve para mandar información ingresada en una vista, a otra, en este caso, HolaAlumnosSpring.
//	@RequestMapping("/procesaFormulario2")
//	public String procesoFormulario(HttpServletRequest request, Model modelo) {
//		
//		String nombre = request.getParameter("nombreAlumno"); // Se trae la información introducida en el jsp HolaAlumnosFormulario
//															  // Hay que recordar que el método getParameter sirve para traer al servidor, información introducida en el frontend (html o jsp).
//		
//		
//		nombre += "es el mejor alumno";
//		
//		String mensajeFinal = "¿Quién es el mejor alumno?" + nombre;
//		
//		modelo.addAttribute("mensajeClaro", mensajeFinal); // El modelo es, en este caso, un puente entre el controlador y una vista. Allá podemos recuperar el valor. Es como una especie de valor de sesión.
//		
//		return "HolaAlumnosSpring";
//	}
	
	
	// El siguiente método es exactamente igual que el anterior que está comentado.
	@RequestMapping("/procesaFormulario2")
	public String procesaFormulario(@RequestParam("nombreAlumno") String nombre, Model modelo) {  //La anotación @RequestParam es exactamente igual a "request.getParameter". Debe ir en los parámetros. Ya está ingresando el valor directamente por los argumentos.
																								// Con @RequestParam el valor desde el front end pasa al servidor (backend) a través de la url (http://localhost:8085/MVC_Maven/procesaFormulario2?nombreAlumno=Rodrigo). Esto es una petición GET.
		nombre += "es el mejor alumno";
		
		String mensajeFinal = "¿Quién es el mejor alumno?" + nombre;
		
		modelo.addAttribute("mensajeClaro", mensajeFinal);     // Estamos enviando información a HolaAlumosSpring, a través del identificador "mensajeClaro.
		
		return "HolaAlumnosSpring";
		
	}
	
	
	
	
	
	@RequestMapping("/muestraFormulario")
	public String muestraFormulario() {
		return "HolaAlumnosFormulario";
	}
	
	

}
