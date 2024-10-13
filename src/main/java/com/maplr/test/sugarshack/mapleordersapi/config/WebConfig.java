package com.maplr.test.sugarshack.mapleordersapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String DATE_TIME_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/public/**")  // Allow CORS for all endpoints
                .allowedOrigins("http://localhost:4200")  // Frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.configOverride(java.time.ZonedDateTime.class)
                    .setFormat(com.fasterxml.jackson.annotation.JsonFormat.Value.forPattern(DATE_TIME_ISO_8601));

        return new MappingJackson2HttpMessageConverter(objectMapper);
    }


    // add interceptor
}
