package com.cyberslex.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cyberslex.api.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {  //Long es el tipo de variable "id" creada en la clase entidad "Persona"


}