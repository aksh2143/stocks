package com.sky.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

//s@Configuration
public class CorsConfig {

    @Value("${app.allowed-origin}")
    List<String> allowedUrls;

    @Bean
    public CorsWebFilter corsWebFilter(){
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(allowedUrls);
        corsConfiguration.setMaxAge(360000L);
        corsConfiguration.setAllowedMethods(Arrays.asList("HEAD","OPTIONS","GET","POST","PUT","PATCH","DELETE"));
        corsConfiguration.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsWebFilter(source);
    }
}
