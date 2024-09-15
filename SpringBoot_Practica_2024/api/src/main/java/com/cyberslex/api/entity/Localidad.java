package com.cyberslex.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localidad")
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localidad extends Base {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="denominacion")
	private String denominacion;

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	
	

}
