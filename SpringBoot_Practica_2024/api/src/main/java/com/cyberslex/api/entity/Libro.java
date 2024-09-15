package com.cyberslex.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

@Entity
@Table(name="libro")
@AllArgsConstructor
@NoArgsConstructor
public class Libro extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="titulo")
	private String titulo;
	
	@Column(name="fecha")
	private int fecha;
	
	@Column(name="genero")
	private String genero;
	
	@Column(name="paginas")
	private int paginas;
	
	
	//El atributo CascadeType.REFRESH sirve pa indicar que todas las operaciones de ACTUALIZACIÓN solamente, en el algún registro de la tabla principal, tendrá efecto sobre la tabla padre.
	@ManyToMany(cascade=CascadeType.REFRESH) // La anotación @ManyToMany automáticamente crea una tabla intermedia. Esto es consistente con las reglas de mapeo. Por eso no es necesario utilizar otra anotaación.
	private List<Autor> autores;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	
	

}
