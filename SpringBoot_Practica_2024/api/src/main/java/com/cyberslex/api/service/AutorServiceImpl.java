package com.cyberslex.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyberslex.api.entity.Autor;
import com.cyberslex.api.repository.AutorRepository;
import com.cyberslex.api.repository.BaseRepository;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Long> implements IAutorService{
	
	@Autowired
	private AutorRepository autorRepository;

	public AutorServiceImpl(BaseRepository<Autor, Long> baseRepository) {
		super(baseRepository);
	}
	
	

}
