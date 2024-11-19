package com.cyberslex.api.entity;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass   //Esta anotación sólo se usa en las clases de tipo entity, y sirve para aclarar que no tiene un mapeo directo a la base de datos como normalmente lo haría, sino que es una pantilla que es usada por otras clases de tipo entity. En las que usan esta plantilla, basta herderar de ella, para que automáticamente pasen sus atributos.
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class Base implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}
