package com.foxmind.stock.infrastructure.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Stock Service API")
            .version("1.0.0")
            .description("Doc of API about Stock with Spring WebFlux and MongoDB Reactive"));
    }
}
