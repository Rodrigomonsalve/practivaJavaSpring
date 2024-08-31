package com.cyberslex.Security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


//ESTE ES EL ARCHIVO DE CONFIGURACIÒN DE SPRING SECURITY. HASTA EL MOMENTO, YA LLEVAMOS 3 ARCHIVOS DE CONFIGURACIÓN.

@Configuration
@EnableWebSecurity  //No bean named 'springSecurityFilterChain' available  ---> Con esta anotación se crea un bean de nombre "springSecurityFilterChain". Si no ponemos esta anotación nos arroja un error.
public class SecurityAppConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder usuario = User.withDefaultPasswordEncoder();
		
//		auth.inMemoryAuthentication().withUser(usuario.username("Juan").password("123").roles("administrador"));
//		auth.inMemoryAuthentication().withUser(usuario.username("Juanito").password("abc").roles("administrador"));
//		auth.inMemoryAuthentication().withUser(usuario.username("Pepe").password("xyz").roles("usuario"));
//		auth.inMemoryAuthentication().withUser(usuario.username("Rorro").password("1234").roles("usuario"));
		
		
		//CREAMOS USUARIOS DE FORMA MANUAL, SÓLO PARA EFECTOS DE PRUEBA. NO ES LA FORMA CORRECTA DE HACERLO.
		//Si nos fijamos, a todos les pusimos rol de "usuario". Esto es porque debemos dar acceso a una zona común a TODOS los usuarios.
		auth.inMemoryAuthentication().withUser(usuario.username("Juan").password("123").roles("usuario", "administrador"));
		auth.inMemoryAuthentication().withUser(usuario.username("Juanito").password("abc").roles("usuario", "ayudante"));
		auth.inMemoryAuthentication().withUser(usuario.username("Pepe").password("xyz").roles("usuario"));
		
		
	}
	
	// Con el siguiente método se configuran roles, pagina de login, logout y página de acceso denegado.

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/miFormularioLogin").loginProcessingUrl("/autenticacionUsuario").permitAll().and().logout().permitAll();
	
		http.authorizeRequests().antMatchers("/").hasRole("usuario")  	// Todos los roles "usuarios" tendrán acceso a la página /
		.antMatchers("/administradores/**").hasRole("administrador")	// Sólo los administradores tendrán acceso a la página de /administradores
		.and().formLogin().loginPage("/miFormularioLogin")				// Esta es la página de login personalizada. Esto obviamente, no está protegido.
		.loginProcessingUrl("/autenticacionUsuario").permitAll()		// Esta la URL a la que se envían las credenciales ingresadas en el login, para que Spring las valide. No es un URL visible. Una vez validadas, dirigirá a la URL solicitada que, en nuestro caso, es /
		.and().logout().permitAll()										// También se configura un logout. No necesitamos obviamente una página en específico para estos efectos.
		.and().exceptionHandling().accessDeniedPage("/acceso-denegado"); 	// Es nuestra página personalizada de acceso denegado.
	
	}
	
	
	
	

}
