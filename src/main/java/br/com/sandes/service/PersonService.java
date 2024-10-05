package br.com.sandes.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sandes.exceptions.ResourceNotFoundException;
import br.com.sandes.model.Person;
import br.com.sandes.repositories.PersonRepository;

@Service
public class PersonService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		logger.info("Finding one person!");
		
		return repository.findAll();
	}

	public Person findById(Long id) {
		logger.info("Finding one person!");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"No records found for this id"));
	}
	
	public Person create(Person person) {
		logger.info("Person created");

		Optional<Person> savedPerson = repository.findByEmail(person.getEmail());

		if(savedPerson.isPresent()){
			throw new ResourceNotFoundException("Person exist for the e-mail: " + person.getEmail());
		}
		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Person updated");
		
		var entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFoundException(
				"No records found for this id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"No records found for this id"));
		
		repository.delete(entity);
	}
}
