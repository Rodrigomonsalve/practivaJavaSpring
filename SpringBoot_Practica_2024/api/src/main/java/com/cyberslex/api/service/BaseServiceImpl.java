package com.cyberslex.api.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cyberslex.api.entity.Base;
import com.cyberslex.api.entity.Persona;
import com.cyberslex.api.repository.BaseRepository;

import jakarta.transaction.Transactional;

// Las clases abastractas no se pueden instanciar, tal como las interfaces. También pueden crear métodos abstractos. De nada sirve instanciar un clase base. Por eso conviene crearla abstracta.
// También las clases abstractas obligan a la clases hijas a implementar todos los métodos abstractos. Esto podría llegar a ser útil.
public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements IBaseService<E, ID> {

	protected BaseRepository<E, ID> baseRepository;

	public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
		super();
		this.baseRepository = baseRepository;
	}
	
	@Override
	@Transactional
	public List<E> findAll() throws Exception {
		try {
			
		List<E> entities = baseRepository.findAll();
		return entities;
		
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
		}
	}

	@Override
	@Transactional
	public E findById(ID Id) throws Exception {
		try {
			Optional<E> entityOptional = baseRepository.findById(Id);
			return entityOptional.get();
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
	}	
	}	

	@Override
	@Transactional
	public E save(E entity) throws Exception {
		try {
			entity = baseRepository.save(entity);
			return entity;
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
	}
	}

	@Override
	@Transactional
	public E update(ID id, E entity) throws Exception {
		try {
			
			Optional<E> entityOptional = baseRepository.findById(id);
			
			E persona = entityOptional.get();
			
			persona = baseRepository.save(entity);
			
			return persona;
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
			
	}
	}

	@Override
	@Transactional
	public boolean delete (ID id) throws Exception {
	
		try {
			
			if(baseRepository.existsById(id)) {
				
				baseRepository.deleteById(id);
				
				return true;
			}else {
				
				throw new Exception();
				
			}
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
	}
		
	}
	
	@Override
	@Transactional
	public Page<E> findAll(Pageable pageable) throws Exception{
		
		try {
			
			Page<E> entities = baseRepository.findAll(pageable);
			return entities;
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
}
