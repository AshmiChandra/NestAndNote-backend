package com.nestandnote.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] origins = Stream.concat(
                Arrays.stream(allowedOrigins.split("\\s*,\\s*")),
                Stream.of("https://nest-and-note-frontend.vercel.app"))
            .distinct()
            .toArray(String[]::new);

        registry.addMapping("/api/**")
            .allowedOriginPatterns(origins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}