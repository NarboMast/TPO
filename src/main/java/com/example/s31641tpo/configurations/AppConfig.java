package com.example.s31641tpo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
class AppConfig {

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    Random random() {
        return new Random();
    }

    @Bean
    List<Object> arrayList(){
        return new ArrayList<>();
    }
}
