package com.udea.persona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.udea.persona.model.Persona;
import com.udea.persona.dao.IPersonaDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PersonaService{
    @Autowired
    private IPersonaDAO dao;

    public Persona save(Persona persona){
        return dao.save(persona);
    }

    public Persona update(Persona persona){
        return dao.save(persona);
    }

    public void delete(Persona persona){
        dao.delete(persona);
    }

    public Iterable<Persona> list(){
        return dao.findAll();
    }

    public Optional<Persona> listId(long id) {
        return dao.findById(id);
    }
    
    public boolean increaseSalary(Persona person) {
        long years2 = 63115200000L;
        long years = Date.valueOf(LocalDate.now()).getTime() - Date.valueOf(person.getStartdate()).getTime();
        if (years >= years2) {
            int newSalary = person.getSalary() * 10 / 100 + person.getSalary();
            person.setSalary(newSalary);
            update(person);
            return true;
            }
        return false;
    }
}