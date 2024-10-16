package br.com.sandes.repositories;

import br.com.sandes.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.sandes.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest extends AbstractIntegrationTest {

	private Person person0;
	
	@Autowired
	PersonRepository repository;

	@BeforeEach
	void setUp(){
		person0 = new Person(
				"Mateus",
				"Sandes",
				"Rua dos Noiados 107 - Recife - Brasil",
				"Male",
				"mateus.sandes@saidae.com.br");
	}
	
	@Test
	@DisplayName("Given Person Object When Save Then Return Save Person")
	void testGivenPersonObject_WhenSave_thenReturnSavePerson() {

		//given
		Person savedPerson = repository.save(person0);

		//then
		assertNotNull(savedPerson);
		assertTrue(savedPerson.getId() > 0);
	}

	@Test
	@DisplayName("Given Person Object When Save Then Return Person List")
	void testGivenPersonObject_WhenSave_thenReturnPersonList() {

		//given
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

	@Test
	@DisplayName("Given Person Object When Save Then Return Person By Id")
	void testGivenPersonObject_WhenFindByEmail_thenReturnSPersonObject() {

		//given
		repository.save(person0);

		Person savedPerson = repository.findByEmail(
				person0.getEmail())
				.get();
		//when

		//then
		assertNotNull(savedPerson);
		assertEquals(savedPerson.getEmail(), "mateus.sandes@saidae.com.br");
	}

	@Test
	@DisplayName("Given Person Object When Update Person Then Return Updated Person Object")
	void testGivenPersonObject_WhenUpdatePerson_thenReturnUpdatedPersonObject() {

		//given
		Person savedPerson = repository.save(person0);

		//when
		var foundPerson = repository.findById(savedPerson.getId());
		savedPerson.setFirstName("Changed Name");
		savedPerson.setEmail("mateus.sandes@peramermao.com.br");

		Person updatedPerson = repository.save(savedPerson);

		//then
		assertNotNull(updatedPerson);
		assertEquals(updatedPerson.getFirstName(), "Changed Name");
		assertEquals(updatedPerson.getEmail(), "mateus.sandes@peramermao.com.br");
	}

	@Test
	@DisplayName("Given Person Object When Delete Then Remove Person")
	void testGivenPersonObject_whenDelete_thenRemovePerson() {

		//given
		repository.save(person0);

		repository.deleteById(person0.getId());

		Optional<Person> personOptional = repository.findById(person0.getId());

		assertTrue(personOptional.isEmpty());
	}

	@Test
	@DisplayName("JUnit for Given First Name And LastName When Find ByJ PQL Then Return Person Object")
	void testGivenFirstNameAndLastName_WhenFindByJPQL_thenReturnPersonObject() {

		//given
		repository.save(person0);

		//when
		var foundPerson = repository.findByJPQL(
				"Mateus",
				"Sandes");

		//then
		assertNotNull(foundPerson);
		assertEquals(foundPerson.getLastName(), "Sandes");
		assertEquals(foundPerson.getFirstName(), "Mateus");
	}

	@Test
	@DisplayName("JUnit for Given First Name And LastName When Find By JPQL With Named Parametrs Then Return Person Object")
	void testGivenFirstNameAndLastName_WhenFindByJPQLNamedParameters_thenReturnPersonObject() {

		//given
		repository.save(person0);

		//when
		var foundPerson = repository.findByJPQLNamedParameters(
				"Mateus",
				"Sandes");

		//then
		assertNotNull(foundPerson);
		assertEquals(foundPerson.getLastName(), "Sandes");
		assertEquals(foundPerson.getFirstName(), "Mateus");
	}

	@Test
	@DisplayName("JUnit for Given First Name And LastName When Find By JPQL With Named Parametrs Then Return Person Object")
	void testGivenFirstNameAndLastName_WhenFindByNativeSQL_thenReturnPersonObject() {

		//given
		repository.save(person0);

		//when
		var foundPerson = repository.findByNativeSQL(
				"Mateus",
				"Sandes");

		//then
		assertNotNull(foundPerson);
		assertEquals(foundPerson.getLastName(), "Sandes");
		assertEquals(foundPerson.getFirstName(), "Mateus");
	}

	@Test
	@DisplayName("JUnit for Given First Name And LastName When Find By JPQL With Named Parametrs Then Return Person Object")
	void testGivenFirstNameAndLastName_WhenFindByNativeSQLAndNameParameters_thenReturnPersonObject() {

		//given
		repository.save(person0);

		//when
		var foundPerson = repository.findByNativeSQLWithNamedParameters(
				"Mateus",
				"Sandes");

		//then
		assertNotNull(foundPerson);
		assertEquals(foundPerson.getLastName(), "Sandes");
		assertEquals(foundPerson.getFirstName(), "Mateus");
	}
}
