package com.maplr.test.sugarshack.mapleordersapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(@Value("${spring.application.name}") String appVersion) {
        return new OpenAPI()
                .info(new Info().title("Maple Orders Api - services").version(appVersion));
    }

}
