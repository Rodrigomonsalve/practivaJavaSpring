package com.cyberslex.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="domicilio")
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio extends Base {

 private static final long serialVersionUID = 1L;
	
	
	@Column(name="calle")
	private String calle;
	
	@Column(name="numero")
	private String numero;
	
	
	// Aquí la entidad fuerte es Localidad, pero tiena cardinalidad 1, por lo que se usa la anotación @ManyToOne y no @OneToMany.
	@ManyToOne(optional=false) // El atributo optional=false significa que no se permitirá que el valor de la llave foránea sea null. Siempre deberá tener un valor.
	@JoinColumn(name="fk_localidad") 
	private Localidad localidad;

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	

}
