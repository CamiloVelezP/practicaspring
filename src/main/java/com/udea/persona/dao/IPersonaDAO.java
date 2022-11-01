package com.udea.persona.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.udea.persona.model.Persona;

@Repository
public interface IPersonaDAO extends CrudRepository<Persona, Long>{

}