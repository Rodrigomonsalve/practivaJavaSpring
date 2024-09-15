package com.cyberslex.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cyberslex.api.entity.Persona;

public interface IPersonaService extends IBaseService<Persona, Long> {
	
	List<Persona> search(String filtro) throws Exception;
	Page<Persona> search(String filtro, Pageable pageable) throws Exception;
	
	
	
	

}
