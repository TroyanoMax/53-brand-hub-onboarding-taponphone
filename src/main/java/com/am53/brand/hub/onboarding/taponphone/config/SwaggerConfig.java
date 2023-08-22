package com.am53.brand.hub.onboarding.taponphone.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components()
//                        .addSecuritySchemes(
//                                "BearerToken",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .name("Bearer token")
//                                        .bearerFormat("JWT")
//                                        .scheme("bearer")
//                                        .description("Bearer token.")
//                        ))
//                .addServersItem(new Server().url("https://localhost:8080").description("Local server"))
//                .addServersItem(new Server().url("https://www.am53.com:8443/53-visa-direct-funds-transactions-api/").description("Dev server"))
//                .addServersItem(new Server().url("https://www.am53.com:8443/53-visa-direct-funds-transactions-api/").description("Uat server"))
//                .addServersItem(new Server().url("https://www.am53.com/53-visa-direct-funds-transactions-api/").description("Pro server"));
//    }
//
//    @Bean
//    public OpenApiCustomiser consumerTypeHeaderOpenAPICustomiser() {
//        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
//                .forEach(operation -> operation.addSecurityItem(new SecurityRequirement().addList("BearerToken")));
//    }

}
