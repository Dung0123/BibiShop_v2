package com.example.bibishop.service.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper
    modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public Module hibernate5Module() {
        Hibernate6Module module = new Hibernate6Module();
        // Tùy chọn: bạn có thể tắt việc force lazy loading nếu không muốn nạp dữ liệu lazy
        module.disable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);
        return module;
    }

}
