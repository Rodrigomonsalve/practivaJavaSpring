package com.cyberslex.API_REST_COMPLETO.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class ProyectoRestController {
	
	@GetMapping("/saludo")
	public String saludoPrueba() {
		
		return "Hola!";
		
	}
	
	
	
	

}
