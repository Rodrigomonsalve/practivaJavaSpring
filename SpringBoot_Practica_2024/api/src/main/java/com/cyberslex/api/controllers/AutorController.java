package com.cyberslex.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberslex.api.entity.Autor;
import com.cyberslex.api.service.AutorServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path= "api/v1/autores")
public abstract class AutorController extends BaseControllerImpl<Autor, AutorServiceImpl>{

}