package br.com.sandes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenApi(){

        return new OpenAPI()
                .info(new Info()
                        .title("Hello Swagger OpenApi")
                        .version("v1")
                        .description("Here is my new Api that will be tested soon!")
                        .termsOfService("https://www.google.com")
                        .license(new License()
                                .name("Apaceh 2.0")
                                .url("")));
    }
}
