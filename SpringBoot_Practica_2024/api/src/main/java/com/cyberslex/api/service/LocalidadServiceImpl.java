package com.cyberslex.api.service;

import org.springframework.stereotype.Service;

import com.cyberslex.api.entity.Localidad;
import com.cyberslex.api.repository.BaseRepository;


@Service
public class LocalidadServiceImpl extends BaseServiceImpl<Localidad, Long> implements ILocalidadService{

	public LocalidadServiceImpl(BaseRepository<Localidad, Long> baseRepository) {   //ESTUDIAR POR QUÈ FORZOSAMENTE DEBE HABER UN CONTRUCTOR AQUÍ
		super(baseRepository);
		// TODO Auto-generated constructor stub
	}

}
