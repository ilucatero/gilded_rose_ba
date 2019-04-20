package com.gildedrose.web.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class WebAppConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0) // TODO : change the cached value > 0 depending on DEV or PROD profiles
                .resourceChain(true);

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/paper-dashboard-react/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("classpath:/paper-dashboard-react/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("classpath:/paper-dashboard-react/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("classpath:/paper-dashboard-react/build/");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("classpath:/paper-dashboard-react/build/index.html");
    }
}
