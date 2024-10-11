package br.com.sandes.integrationtests.swagger;

import br.com.sandes.config.TestConfig;
import br.com.sandes.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@DisplayName("JUnit Should Display Swagger Ui Page")
	@Test
	void testShouldDisplaySwaggerUiPage() {

		var content = given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfig.SERVER_PORT)
		.when()
			.get()
		.then()
				.statusCode(200)
			.extract()
				.body()
				.asString();

		assertTrue(content.contains("Swagger UI"));
	}
}
