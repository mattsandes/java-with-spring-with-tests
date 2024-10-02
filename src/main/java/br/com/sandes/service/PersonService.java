package br.com.sandes.service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.sandes.model.Person;

@Service
public class PersonService {
	
	private static final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
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
}
