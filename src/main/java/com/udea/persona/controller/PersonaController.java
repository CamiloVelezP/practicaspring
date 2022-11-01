package com.udea.persona.controller;

import com.udea.persona.exception.ModelNotFoundException;
import com.udea.persona.model.Persona;
import com.udea.persona.service.PersonaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
 
@RestController
@RequestMapping("/persona")
@CrossOrigin("*")
public class PersonaController {
    
    
   @Autowired
   PersonaService personService;
   
   @PostMapping("/save")
   public long save(@RequestBody Persona person){
      personService.save(person);
      return person.getIdPerson();
   }
   
   @GetMapping("/listAll")
   public Iterable<Persona> listAllPersons(){return personService.list();}
   
   @GetMapping("/list/{id}")
   public Persona listPersonById(@PathVariable("id") int id) {
      Optional<Persona> person = personService.listId(id);
      if (person.isPresent()) {
         return person.get();
      }

      throw new ModelNotFoundException("ID de persona invalido");
   }
   
   @PutMapping("/person/{id}")
   public long replacePersona(@RequestBody Persona newPerson, @PathVariable("id") Long id) {
      Optional<Persona> person = personService.listId(id);
      return person.map(persona -> {
         persona.setFirstName(newPerson.getFirstName());
         persona.setLastName(newPerson.getLastName());
         persona.setEmail(newPerson.getEmail());
         persona.setAddress(newPerson.getAddress());
         persona.setDependence(newPerson.getDependence());
         persona.setOffice(newPerson.getOffice());
         persona.setPosition(newPerson.getPosition());
         persona.setSalary(newPerson.getSalary());
         persona.setStartdate(newPerson.getStartdate());
         personService.update(persona);
         return persona.getIdPerson();
      }).orElseGet(() -> {
         newPerson.setIdPerson(id);
         personService.save(newPerson);
         return newPerson.getIdPerson();
      });
   }

   @DeleteMapping("/person/{id}")
   public void deletePerson(@PathVariable("id") Long id) {
      Optional<Persona> person = personService.listId(id);
      if (person.isPresent()) {
         personService.delete(person.get());
      } else {
         throw new ModelNotFoundException("ID de persona invalido");
      }
   }
   
   @PutMapping("person/increasesalary/{id}")
   public void increaseSalary(@PathVariable("id") long id) {
      Optional<Persona> person = personService.listId(id);
      if (person.isPresent()) {
         personService.increaseSalary(person.get());
      } else {
         throw new ModelNotFoundException("ID de persona invalido");
      }
   }
}
