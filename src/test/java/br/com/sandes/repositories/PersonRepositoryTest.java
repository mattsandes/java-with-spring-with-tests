package br.com.sandes.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.sandes.model.Person;

@DataJpaTest
class PersonRepositoryTest {
	
	@Autowired
	PersonRepository repository;
	
	@Test
	void testGivenPersonObject_WhenSave_thenReturnSavePerson() {
		
		//given
		Person person0 = new Person(
				"Mateus",
				"Sandes",
				"Rua dos Noiados 107 - Recife - Brasil",
				"Male",
				"mateus.sandes@saidae.com.br");
		
		//when
		Person savedPerson = repository.save(person0);
		
		//then
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}
}
