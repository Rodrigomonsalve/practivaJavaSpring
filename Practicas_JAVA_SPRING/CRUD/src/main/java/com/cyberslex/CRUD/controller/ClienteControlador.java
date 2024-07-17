package com.cyberslex.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyberslex.CRUD.config.Cliente;
import com.cyberslex.CRUD.dao.ClienteDAO;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@RequestMapping("/lista")
	public String listaClientes(Model elModelo) {
		
		List<Cliente> losClientes = clienteDAO.getClientes();
		
		elModelo.addAttribute("cliente", losClientes);
		
		return "lista-clientes";
		
		
	}
	

}
