package com.cyberslex.api.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.cyberslex.api.entity.Base;

@NoRepositoryBean   // Esta anotación provoca que esta clase no se transforme en un bean, ya que, las clases que nos interesa que lo sean, son las que se van a basar en este clase, en este caso, AutorRepository y PersonaRepository.
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID>{

}
