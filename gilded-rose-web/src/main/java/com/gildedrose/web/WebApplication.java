package com.gildedrose.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("spring-bean-web-conf.xml")
public class WebApplication {

    public static void main(String[] args) {
            SpringApplication.run(WebApplication.class, args);
    }

}
