package com.movie.crud.rest;

import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class TestApplicationConfiguration {

    @Bean
    public RestTemplate testRestTemplate() {
        RestTemplate restTemplate = new TestRestTemplate();
        return restTemplate;
    }
}
