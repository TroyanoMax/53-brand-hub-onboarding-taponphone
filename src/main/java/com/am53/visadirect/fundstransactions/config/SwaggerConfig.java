package com.am53.visadirect.fundstransactions.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(
                                "BearerToken",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .name("Bearer token")
                                        .bearerFormat("JWT")
                                        .scheme("bearer")
                                        .description("Bearer token.")
                        ))
                .addServersItem(new Server().url("http://localhost:8080").description("Local server"))
                .addServersItem(new Server().url("https://www.am53.com:8443/53-visa-direct-funds-transactions-api/").description("Dev server"))
                .addServersItem(new Server().url("https://www.am53.com:8443/53-visa-direct-funds-transactions-api/").description("Uat server"))
                .addServersItem(new Server().url("https://www.am53.com/53-visa-direct-funds-transactions-api/").description("Pro server"));
    }

    @Bean
    public OpenApiCustomiser consumerTypeHeaderOpenAPICustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.addSecurityItem(new SecurityRequirement().addList("BearerToken")));
    }

}
