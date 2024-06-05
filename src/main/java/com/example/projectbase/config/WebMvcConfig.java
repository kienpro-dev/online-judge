package com.example.projectbase.config;

import com.example.projectbase.converter.DateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateTimeConverter());
    }

    @Bean
    private DateTimeConverter dateTimeConverter() {
        return new DateTimeConverter("HH:mm:ss, dd/MM/yyyy");
    }
}

