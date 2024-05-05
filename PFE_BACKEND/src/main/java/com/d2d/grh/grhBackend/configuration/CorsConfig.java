package com.d2d.grh.grhBackend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/notification-endpoint")
                .allowedOrigins("http://localhost:4201") // Replace with your client's URL
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
