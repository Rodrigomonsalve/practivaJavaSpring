package com.cyberslex.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cyberslex.api.entity.Persona;
import com.cyberslex.api.repository.BaseRepository;
import com.cyberslex.api.repository.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
//public class PersonaService implements IBaseService<Persona> {
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long> implements IPersonaService{
	
	@Autowired
	private PersonaRepository personaRepository;           // AQUÍ ESTAMOS INYECTANDO UNA DEPENDENCIA. DEBEMOS HACER ESTO, PORQUE BASEREPOSITORY USA UNA ANOTACIÓN PARA NO SER CONSIDERADO COMO BEAN, POR LO QUE ES NECESARIO QUE PODAMOS ACCEDER A LOS MÉTODOS DEL REPOSITORIO, EN ESTE CASO, DE PERSONA.
	
//	public PersonaService(PersonaRepository personaRepository) {
//		this.personaRepository=personaRepository;
//	}
	
	public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {  //  Desde aquí, pasamos una variable de nombre baseRepository a la clase padre (BaseServiceImpl) para que ahí pueda trabajar con ella en todos sus métodos.
		super(baseRepository);													// Esta es otra forma de inyectar dependencias no vista antes. BaseRepository es la interfaz padre de PersonaRepository. Debido a que inyectamos PersonaRepository en esta clase, lo que en realidad pasa por parámetros del constructor a la clase BaseServiceImpl, es personaRepository; no BaseRepository.
	}

	@Override
	public List<Persona> search(String filtro) throws Exception {
		try {
			
			//List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro, filtro);
			
			//List<Persona> personas = personaRepository.search(filtro);
			
			List<Persona> personas = personaRepository.searchNativo(filtro);
			return personas;
		}catch(Exception e){
			throw new Exception(e.getMessage());
			
			
		}
	}

	@Override
	public Page<Persona> search(String filtro, Pageable pageable) throws Exception {
		try {
			Page<Persona> personas = personaRepository.searchNativo(filtro, pageable);
			return personas;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
			
		}
	
	}
	

//	@Override
//	@Transactional
//	public List<Persona> findAll() throws Exception {
//		try {
//			
//		List<Persona> entities = personaRepository.findAll();
//		return entities;
//		
//		}catch(Exception e) {
//			
//			throw new Exception(e.getMessage());
//			
//		}
//	}

//	@Override
//	@Transactional
//	public Persona findById(Long Id) throws Exception {
//		try {
//			Optional<Persona> entityOptional = personaRepository.findById(Id);
//			return entityOptional.get();
//			
//		}catch(Exception e) {
//			throw new Exception(e.getMessage());
//	}	
//	}	
//
//	@Override
//	@Transactional
//	public Persona save(Persona entity) throws Exception {
//		try {
//			entity = personaRepository.save(entity);
//			return entity;
//			
//		}catch(Exception e) {
//			
//			throw new Exception(e.getMessage());
//	}
//	}
//
//	@Override
//	@Transactional
//	public Persona update(Long id, Persona entity) throws Exception {
//		try {
//			
//			Optional<Persona> entityOptional = personaRepository.findById(id);
//			
//			Persona persona = entityOptional.get();
//			
//			persona = personaRepository.save(entity);
//			
//			return persona;
//			
//		}catch(Exception e) {
//			
//			throw new Exception(e.getMessage());
//			
//			
//	}
//	}
//
//	@Override
//	@Transactional
//	public boolean delete(Long id) throws Exception {
//	
//		try {
//			
//			if(personaRepository.existsById(id)) {
//				
//				personaRepository.deleteById(id);
//				
//				return true;
//			}else {
//				
//				throw new Exception();
//				
//			}
//			
//		}catch(Exception e) {
//			
//			throw new Exception(e.getMessage());
//			
//	}
//		
//	}

}
