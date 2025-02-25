package com.proyecto_inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfiguration() { // 🔹 Renombrado de "corsConfigurer" a "corsConfiguration"
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 🔹 Aplica CORS a todas las rutas
                        .allowedOrigins("http://localhost:3000") // 🔹 Permite solicitudes desde React
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 🔹 Métodos HTTP permitidos
                        .allowedHeaders("*") // 🔹 Permite todos los headers
                        .allowCredentials(true); // 🔹 Permite autenticación con cookies o JWT
            }
        };
    }
}
