package com.cyberslex.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberslex.api.entity.Autor;
import com.cyberslex.api.service.AutorServiceImpl;

@RestController //PROVOCA QUE LOS METODOS SIEMPRE DEVUELVAN UN JSON. POR DEFECTO ES EN JSON. SI QUIERES QUE SOPORTE XML, DEBERÁS CONFIGURAR TU MÉTODO CONTROLADOR Y AGREGAR EN EL POM LA LIBRERÍA jackson-dataformart-xml 
// DE IGUAL FORMA, LA SOLICITUD HTTP DEL CLIENTE DEBE SOLICITAR UN JSON DENTRO DEL VALOR "ACCEPT"   */* ---> acepta todos los formatos , application/json ---> sólo acepta json , application/xml ---> sólo acepta xml
@CrossOrigin(origins = "*")
@RequestMapping(path= "api/v1/autores")
public abstract class AutorController extends BaseControllerImpl<Autor, AutorServiceImpl>{

}
