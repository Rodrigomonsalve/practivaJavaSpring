package com.cyberslex.MVC_Maven.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

// ESTA CLASE ESTA RELACIONADA CON EL TEMA DE MVC TAGS

@Controller
@RequestMapping("/alumno")
public class ControladorAlumno {
	
	
	@InitBinder
	public void miBinder(WebDataBinder binder) {
		
		StringTrimmerEditor recortaEspaciosBlanco = new StringTrimmerEditor(true);
		
		binder.registerCustomEditor(String.class, recortaEspaciosBlanco);
	}
	
	
	@RequestMapping("/muestraFormulario")
	public String muestraFormulario(Model modelo) {
	
	Alumno elAlumno=new Alumno();
	
	modelo.addAttribute("elAlumno", elAlumno);  // En este caso no estamos enviado información a la vista como lo hicimos en ControladorMyLogin, sino que estamos enviando un objeto nuevo vacío para trabajar con él en la vista. En este caso a alumnoRegistraFormulario, en donde existe el atributo de etiqueta modelAttribute con el valor "elAlumno".
												// Podíamos haber usado también el método request.getParameterValues, pero es más sencillo trabajar en este caso con un objeto (elAlumno). Con objetos trabajamos con variable-valor. Con request.getParamValues sólo con valores (Strings).
	return "alumnoRegistroFormulario";
	
	
	}
	
	
	@RequestMapping("/procesaFormulario")
	public String procesaFormulario(@Valid @ModelAttribute("elAlumno") Alumno elAlumno, BindingResult resultadoValidacion) {  //Funciona muy parecido a la anotación @RequestParam que utilizamos en la clase ControladorMyLogin. El objeto elAlumno de este método es diferente al del método pasado. Lo que el usuario puso en la vista (alumnoRegistroFormulario) viaja a través del identificador "elAlumno" y se guarda en el objeto elAlumno.
		
		if(resultadoValidacion.hasErrors()) {
			return "alumnoRegistroFormulario";
		}else {
		
		return "confirmacionRegistroAlumno";
			}
	}
}
