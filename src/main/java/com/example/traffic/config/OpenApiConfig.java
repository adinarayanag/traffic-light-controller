package com.example.traffic.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI trafficLightOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Traffic Light Controller API")
                        .version("1.0.0")
                        .description("Multi-intersection Traffic Light Management System with concurrency control and safety validation.")
                        .contact(new Contact()
                                .name("Adinarayana G")
                                .email("gandikota.adi@gmail.com"))
                        .license(new License()
                                .name("Internal Coding Assessment"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Environment")
                ))
                .tags(List.of(
                        new Tag().name("Traffic Management").description("Operations related to traffic light control")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/YOUR_USERNAME/traffic-light-controller"));
    }
}