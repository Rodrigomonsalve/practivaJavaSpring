package com.cyberslex.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyberslex.CRUD.dao.ClienteDAO;
import com.cyberslex.CRUD.dao.ClienteDAOClase;
import com.cyberslex.CRUD.entity.Clientes;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
	
	//ALGO NUEVO QUE VEMOS EN ESTE PROYECTO, ES QUE LA CREACIÓN DE BEANS SE REALIZA DE FORMA MIXTA: DE MANERA EXPRESA EN EL CONTENDEOR DE BEANS (SPRING-CRUD-SERVLET-XML) Y EN FORMA DE ANOTACIÓN (@REPOSITORY).
	
	@Autowired
	private ClienteDAO clienteDAO; //Hay que recordar que lo que se inyecta debe ser una interfaz.
	
//	ClienteDAOClase clienteDAO = new ClienteDAOClase();  //Hay que recordar que esto no se puede hacer, debido a que, como a ClienteDAOClase, a su vez, se inyecta SessionFactory. Esta explicación viene en el proyecto Estudio. Hay que respetar la cadena de inyecciones.
	
	@RequestMapping("/lista")
	public String listaClientes(Model elModelo) {
		
		List<Clientes> losClientes = clienteDAO.getCliente();
		
		elModelo.addAttribute("clientes", losClientes);
		
		return "lista-clientes";
		
		
	}
	
	@RequestMapping("/muestraFormularioAgregar")
	public String muestraFormularioAgregar(Model elModelo) {
		
		Clientes elCliente = new Clientes();
		
		elModelo.addAttribute("cliente", elCliente);
		
		return "fomularioCliente";
	}
	
	
	@PostMapping("/insertarCliente")
	public String insertarCliente(@ModelAttribute("cliente") Clientes elCliente) {
		
		clienteDAO.insertaCliente(elCliente);
		
		return "redirect:/cliente/lista";
	}
	
	
	
	
	@GetMapping("/muestraFormularioActualizar")
	public String muestraFormularioActualizar(@RequestParam("clienteId") int Id, Model elModelo) {
		
		Clientes elCliente = clienteDAO.getCliente(Id);
		
		elModelo.addAttribute("cliente", elCliente);
		
		return "fomularioCliente";
	}
	
	
	
	

}
