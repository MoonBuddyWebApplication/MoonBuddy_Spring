package com.project.moonbuddy.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("definitions")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springApi() {
        return new OpenAPI()
                .info(new Info().title("MoonBuddy API")
                        .description("This is the MoonBuddy API")
                        .version("v1"));
    }
}