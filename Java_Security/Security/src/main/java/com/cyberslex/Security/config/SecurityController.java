package com.cyberslex.Security.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/")   //Esta es la primer página en aparecer y será la zona común para todos los roles. Sin embargo, estará protegida por el login.
	public String inicio() {
		
		return "inicio";
	}
	
	@GetMapping("/miFormularioLogin")
	public String muestraLoginPropio() {
		
		return "loginPropio";
	}
	
	@GetMapping("/administradores")
	public String muestraAdministradores() {
		
		return "administradores";
	}
	
	@GetMapping("/usuarios")
	public String muestraUsuarios() {
		
		return "usuario";
	}
	
	@GetMapping("/acceso-denegado")
	public String muestraAccesoDenegado() {
		
		return "acceso-denegado";
	}

}
