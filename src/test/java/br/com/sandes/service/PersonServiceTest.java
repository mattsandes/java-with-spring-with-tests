package br.com.sandes.service;

import br.com.sandes.exceptions.ResourceNotFoundException;
import br.com.sandes.model.Person;
import br.com.sandes.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private Person person0;

    @BeforeEach
    void setUp(){
        person0 = new Person(
                "Mateus",
                "Sandes",
                "Rua dos Noiados 107 - Recife - Brasil",
                "Male",
                "mateus.sandes@saidae.com.br");
    }

    @DisplayName("Given Person Object When Save Person Then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject(){

        //Given (Arrange)
        given(repository.save(person0)).willReturn(person0);
        given(repository.findByEmail(anyString())).willReturn(Optional.empty());

        //When (Act)
        Person savedPerson = service.create(person0);

        //Then (Assert)
        assertNotNull(savedPerson);
        assertEquals(savedPerson.getEmail(), "mateus.sandes@saidae.com.br");
    }

    @DisplayName("Given Existing Email When Save Person Then Throws Exception")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenThrowsException(){

        //Given (Arrange)
        given(repository.findByEmail(anyString()))
                .willReturn(Optional.of(person0));

        //When (Act)
        assertThrows(ResourceNotFoundException.class, () -> {
            service.create(person0);
        });

        //Then (Assert)
        verify(repository, never()).save(person0);
    }

    @DisplayName("Given Persons Lists When Find All Persons then Return Persons List")
    @Test
    void testGivenPersonsLists_WhenFindAllPersons_thenReturnPersonsList(){

        //Given (Arrange)
        Person person1 = new Person(
                "Rebeca",
                "Sandes",
                "Rua dos Noiados 107 - Recife - Brasil",
                "Female",
                "rebeca.sandes@saidae.com.br");

        given(repository.findAll()).willReturn(List.of(person0, person1));

        //When (Act)
        List<Person> result = service.findAll();

        //Then (Assert)
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @DisplayName("Given Persons Lists When Find All Persons then Return Empty Persons List")
    @Test
    void testGivenEmptyPersonsLists_WhenFindAllPersons_thenReturnEmptyPersonsList(){

        //Given (Arrange)
        given(repository.findAll()).willReturn(Collections.emptyList());

        //When (Act)
        List<Person> result = service.findAll();

        //Then (Assert)
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());
    }

    @DisplayName("Given Person Id When Finding By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindingById_thenReturnPersonObject(){

        //Given (Arrange)
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        //When (Act)
        Person savedPerson = service.findById(1L);

        //Then (Assert)
        assertNotNull(savedPerson);
        assertEquals(savedPerson.getFirstName(), "Mateus");
    }

    @DisplayName("Given Person Id When Finding By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenUpdatingPerson_thenReturnUpdatedPersonObject(){

        //Given (Arrange)
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setEmail("mateus.sandes.lisboa@saidae.com.br");
        person0.setLastName("Alves");

        given(repository.save(person0)).willReturn(person0);

        //When (Act)
        Person updatePerson = service.update(person0);

        //Then (Assert)
        assertEquals("Alves", updatePerson.getLastName());
        assertEquals("mateus.sandes.lisboa@saidae.com.br", updatePerson.getEmail());
    }

    @DisplayName("Given Person Id When Delete Person Then Do Nothing")
    @Test
    void testGivenPersonId_WhenDeletePerson_thenDoNothing(){

        //Given (Arrange)
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        //When (Act)
        service.delete(person0.getId());

        //Then (Assert)
        verify(repository, times(1)).delete(person0);
    }
}
