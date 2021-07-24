package com.wmdm.linksforyoutube.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build().enableUrlTemplating(false);
    }

    private ApiInfo apiInfo() {

        return new ApiInfo("API - Car - Spring Boot 2.1.3",
                "API - Spring Boot, Spring Date, Content Negotiation , HATEOAS , SWAGGER", "v1", "Terms Of Service Url",
                "Wedson Martins", "Licence Of API", "License of URL");
    }

}