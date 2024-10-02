package br.com.sandes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.sandes.model.Person;

@Service
public class PersonService {
	
	private static final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll() {
		logger.info("Finding one person!");
		
		List<Person> persons = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			
			persons.add(person);
		}
		
		return persons ;
	}

	public Person findById(String id) {
		logger.info("Finding one person!");
		
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Mateus");
		person.setLastName("Sandes");
		person.setAddress("Recife-PE");
		person.setGender("Male");
		
		return person;
	}
	
	public Person create(Person person) {
		logger.info("Person created");
		
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Person updated");
		
		return person;
	}
	
	public void delete(String id) {
		logger.info("Deleting one person");
	}
	
	private Person mockPerson(int i) {
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Sandes " + i);
		person.setAddress("Some address in Brazil " + i);
		person.setGender("Male");
		
		return person;
	}
	
}
