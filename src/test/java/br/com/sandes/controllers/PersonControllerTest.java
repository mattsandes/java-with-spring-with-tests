package br.com.sandes.controllers;

import br.com.sandes.exceptions.ResourceNotFoundException;
import br.com.sandes.model.Person;
import br.com.sandes.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService service;

    @Autowired
    private ObjectMapper mapper;

    private Person person0;

    @BeforeEach()
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
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {

        //Given (Arrange)
        given(service.create(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        //When (Act)
        ResultActions response = mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person0)));

        //Then (Assert)
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person0.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person0.getLastName())))
                .andExpect(jsonPath("$.email", is(person0.getEmail())));
    }

    @DisplayName("Given Person Object List When Find All Persons Then Return Person Object List")
    @Test
    void testGivenPersonObjectList_WhenFindAllPersons_thenReturnPersonObjectList() throws Exception {

        //Given (Arrange)
        List<Person> personList = new ArrayList<>();

        personList.add(person0);

        Person person1 = new Person(
                "Rebeca",
                "Alves",
                "Rua dos Noiados 107 - Recife - Brasil",
                "Female",
                "rebeca.alves@saidae.com.br");

        given(service.findAll()).willReturn(personList);

        //When (Act)
        ResultActions response = mockMvc.perform(get("/person"));

        //Then (Assert)
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(personList.size())));
    }

    @DisplayName("Given Person Id When Find By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_thenReturnPersonObject() throws Exception {

        //Given (Arrange)
        Long personId = 1L;
        given(service.findById(personId)).willReturn(person0);

        //When (Act)
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        //Then (Assert)
        response
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(person0.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person0.getLastName())))
                .andExpect(jsonPath("$.email", is(person0.getEmail())));
    }

    @DisplayName("Given Invalid Person Id When Find By Id Then Return Not Found")
    @Test
    void testGivenInvalidPersonId_WhenFindById_thenReturnNotFound() throws Exception {

        //Given (Arrange)
        Long personId = 1L;
        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);

        //When (Act)
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        //Then (Assert)
        response
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @DisplayName("Given Update Person When Update Person Then Return Updated PersonObject")
    @Test
    void testGivenUpdatePerson_WhenUpdatePerson_thenReturnUpdatedPersonObject() throws Exception {
        //Given (Arrange)
        Long personId = 1L;

        given(service.findById(personId)).willReturn(person0);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        Person updatePerson = new Person(
                "Mateus Doido",
                "Alves",
                "Rua dos Noiados 107 - Recife - Brasil",
                "Male",
                "mateus.alves@saidae.com.br");

        //When (Act)
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatePerson)));

        //Then (Assert)
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName",is(updatePerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatePerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatePerson.getEmail())));
    }

    @DisplayName("Given Update Person When Update Person Then Return Updated PersonObject")
    @Test
    void testGivenUnexistentPerson_WhenUpdatePerson_thenReturnNotFound() throws Exception {
        //Given (Arrange)
        Long personId = 1L;

        given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(service.update(any(Person.class)))
                .willAnswer((invocation) -> invocation.getArgument(1));

        Person updatePerson = new Person(
                "Mateus Doido",
                "Alves",
                "Rua dos Noiados 107 - Recife - Brasil",
                "Male",
                "mateus.alves@saidae.com.br");

        //When (Act)
        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatePerson)));

        //Then (Assert)
        response
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
