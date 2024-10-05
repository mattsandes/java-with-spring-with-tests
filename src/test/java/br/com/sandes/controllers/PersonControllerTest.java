package br.com.sandes.controllers;

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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
