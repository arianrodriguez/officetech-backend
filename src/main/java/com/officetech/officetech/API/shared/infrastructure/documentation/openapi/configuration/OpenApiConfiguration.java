package com.officetech.officetech.API.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Swagger: http://localhost:8080/swagger-ui/index.html
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("OfficeTech API")
                        .description("API for the endpoints to OfficeTech application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                ).externalDocs(new ExternalDocumentation()
                        .description("SpringDoc OpenAPI 3.0")
                        .url("https://springdoc.org/"));
        return openApi;
    }
}
