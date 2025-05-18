package com.example.bibishop.config;

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

//    @Bean
//    public Module hibernate5Module() {
//        Hibernate5Module module = new Hibernate5Module();
//        // Tùy chọn: bạn có thể tắt việc force lazy loading nếu không muốn nạp dữ liệu lazy
//        module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
//        return module;
//    }



}
