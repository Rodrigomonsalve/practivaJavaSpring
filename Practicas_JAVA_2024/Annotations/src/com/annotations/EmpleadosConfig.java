package com.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Este código hace la veces del archivo de configuración xml, en el que establecíamos beans y, en su caso, en qué clase se inyectarían. Ya no lo necesitamos.
// Ahora en este código establecemos solamente en qué paquete se buscarán beans. En nuestro caso es com.annotations.
// Este código sabe reconocer si son beans, porque en las clases respectivas se coloca la anotación @Component.

@Configuration
@ComponentScan("com.annotations")
@PropertySource("classpath:datosEmpresa.properties") // (Tema diferente a inversión de control e inyección de dependencias) La anotación @PropertySource sirve para que podamos establecer cuál es nuestro archivo "properties". Este archivo guarda valores de forma independiente al código. Lo estamos utilizando en la clase DirectorFinanciero.
public class EmpleadosConfig {
	
	//La anotación @Bean sirve para inyectar dependencias pero desde este archivo de configuración (a diferencia de @Autowired), tal y como lo hicimos en el archivo de configuaración xml (proyecto Estudio).
	//En este caso estamos inyectando un objeto de tipo CreacionInformeFinanciero dentro de DirectorFinanciero, para lo cual utilizamos 2 @Beans.
	@Bean
	public CreacionInformeFinanciero informeFinanciero() {
		return new Informe();
	}
	
	@Bean
	public Empleados directorFinanciero() {
		return new DirectorFinanciero(informeFinanciero()); //Estamos aprovechando el metodo constructor que creamos en la clase DirectorFinanciero.
	}

}
