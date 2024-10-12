package br.com.sandes.integrationtests.controller;

import br.com.sandes.config.TestConfig;
import br.com.sandes.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.sandes.model.Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static Person person;

    @BeforeAll
    static void setUp(){
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfig.SERVER_PORT)
                    .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                    .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "Mateus",
                "Sandes",
                "Rua dos Noia - 109 - Recife - Brasil",
                "Male",
                "mateus.sandes@aidento.com.br");
    }

    @DisplayName("Given Person Object When Create One Person Should Return A Person Object")
    @Test
    @Order(1)
    void integrationTestGivenPersonObject_when_CreateOnePersonShouldReturnAPersonObject()
            throws Exception {

        var content = given().spec(specification)
                .contentType(TestConfig.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                    .post()
                .then()
                    .statusCode(200)
                    .extract()
                        .body()
                .asString();

        Person createdPerson = mapper.readValue(content, Person.class);

        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getGender());
        assertNotNull(createdPerson.getEmail());

        assertTrue(createdPerson.getId() > 0);
        assertEquals("Mateus", createdPerson.getFirstName());
        assertEquals("Sandes", createdPerson.getLastName());
        assertEquals("mateus.sandes@aidento.com.br", createdPerson.getEmail());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("Rua dos Noia - 109 - Recife - Brasil", createdPerson.getAddress());
    }
}
