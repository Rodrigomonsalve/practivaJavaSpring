package com.cyberslex.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cyberslex.api.entity.Persona;
import com.cyberslex.api.repository.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonaService implements IBaseService<Persona> {
	
//	@Autowired
//	private PersonaRepository personaRepository;   // MISMA FORMA DE INYECTAR LA DEPENDENCIA.
	
	
	private PersonaRepository personaRepository;           // AQUÍ ESTAMOS INYECTANDO UNA DEPENDENCIA. SIMPLEMENTE SE USA PARA NO TENER QUE ESTAR INSTANCIANDO PERSONAREPOSITORY. ESO ES TODO. NO SE APROVECHA NINGUNA INTERFAZ, LO QUE SUCEDE A LO LARGO DE TODO ESTE PROYECTO.
	
	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository=personaRepository;
	}

	@Override
	@Transactional
	public List<Persona> findAll() throws Exception {
		try {
			
		List<Persona> entities = personaRepository.findAll();
		return entities;
		
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
		}
	}

	@Override
	@Transactional
	public Persona findById(Long Id) throws Exception {
		try {
			Optional<Persona> entityOptional = personaRepository.findById(Id);
			return entityOptional.get();
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
	}	
	}	

	@Override
	@Transactional
	public Persona save(Persona entity) throws Exception {
		try {
			entity = personaRepository.save(entity);
			return entity;
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
	}
	}

	@Override
	@Transactional
	public Persona update(Long id, Persona entity) throws Exception {
		try {
			
			Optional<Persona> entityOptional = personaRepository.findById(id);
			
			Persona persona = entityOptional.get();
			
			persona = personaRepository.save(entity);
			
			return persona;
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
			
	}
	}

	@Override
	@Transactional
	public boolean delete(Long id) throws Exception {
	
		try {
			
			if(personaRepository.existsById(id)) {
				
				personaRepository.deleteById(id);
				
				return true;
			}else {
				
				throw new Exception();
				
			}
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
			
	}
		
	}

}