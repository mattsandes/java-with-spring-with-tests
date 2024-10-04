package br.com.sandes.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.sandes.model.Person;

import java.util.List;

@DataJpaTest
class PersonRepositoryTest {
	
	@Autowired
	PersonRepository repository;
	
	@Test
	@DisplayName("Given Person Object When Save Then Return Save Person")
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

	@Test
	@DisplayName("Given Person Object When Save Then Return Person List")
	void testGivenPersonObject_WhenSave_thenReturnPersonList() {

		//given
		Person person0 = new Person(
				"Mateus",
				"Sandes",
				"Rua dos Noiados 107 - Recife - Brasil",
				"Male",
				"mateus.sandes@saidae.com.br");

		Person person1 = new Person(
				"Rebeca",
				"Sandes",
				"Rua dos Noiados 107 - Recife - Brasil",
				"Male",
				"mateus.sandes@saidae.com.br");

		repository.save(person0);
		repository.save(person1);

		//when
		List<Person> personList = repository.findAll();

		//then
		assertNotNull(personList);
		assertEquals(2, personList.size());
	}

	@Test
	@DisplayName("Given Person Object When Save Then Return Person By Id")
	void testGivenPersonObject_WhenSave_thenReturnSavePersonById() {

		//given
		Person person0 = new Person(
				"Mateus",
				"Sandes",
				"Rua dos Noiados 107 - Recife - Brasil",
				"Male",
				"mateus.sandes@saidae.com.br");

		Person savedPerson = repository.save(person0);

		var foundPeson = repository.findById(savedPerson.getId());
		//when

		//then
		assertNotNull(savedPerson);
		assertEquals(savedPerson.getFirstName(), "Mateus");
		assertEquals(savedPerson.getLastName(), "Sandes");
		assertEquals(savedPerson.getAddress(), "Rua dos Noiados 107 - Recife - Brasil");
		assertEquals(savedPerson.getGender(), "Male");
		assertEquals(savedPerson.getEmail(), "mateus.sandes@saidae.com.br");
	}
}
