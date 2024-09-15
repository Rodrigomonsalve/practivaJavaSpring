package com.cyberslex.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyberslex.api.entity.Localidad;
import com.cyberslex.api.service.LocalidadServiceImpl;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadServiceImpl> {

}
