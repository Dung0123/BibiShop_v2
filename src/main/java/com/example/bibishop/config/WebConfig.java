package com.example.bibishop.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img2/**")
                .addResourceLocations("file:C:/Users/LENOVO/Desktop/DATN_3/DATN/src/main/resources/static/img2/");
    }
}
