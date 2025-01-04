package com.cyberslex.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//SPRINGBOOT PRESENTA LAS SIGUIENTES VENTAJAS SOBRE SPRINGFRAMEWORK CON ANOATCIONES:
//1)NO REQUIERE CONFIGURAR MANUALMENTE ARCHIVOS XML O CLASES JAVA PARA DEFINIR BEANS (applicationContext.xml y clases en las que se usan anotaciones como @Configuration,@ComponentScan,@PropertySource
//2)INICIALMENTE NO NECESITAS INCLUIR MANUALMENTE LAS DEPENDENCIAS EN EL POM.
//3)TRAE SERVIDOR DE APLICACIONES EMEBEBIDO.
//4)PROPORCIONA UN ARCHIVO APPLICATION.PROPERTIES O APPLICATION.YML
//5)NO NECESITAS ESTRUCTURAR MANUALMENTE EL PROYECTO. SPRING INITIALIZER YA TE DA LA ESTRUCTURA.
//6)EL POM USA "STARTERS" PARA AGRUPAR DEPENDENCIAS COMUNES. LOS "STARTERS" NO LOS VAMOS A ENCONTRAR FUERA DE SPRINGBOOT.
//7)EL POM  GESTIONA LAS VERSIONES DE LAS DEPENDENCIAS AUTOMÁTICAMENTE.
//8)PUEDES EJECUTAR APLICACIONES CON UN ARCHIVO JAR, Y NO SÓLO CON WARS.

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

}
