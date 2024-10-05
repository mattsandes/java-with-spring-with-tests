package br.com.sandes.service;

import br.com.sandes.model.Person;
import br.com.sandes.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

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

    @DisplayName("Test name/Test class name")
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
}
