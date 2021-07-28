package com.wmdm.linksforyoutube.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
//@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiInfo())
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .select()
//	      .apis(RequestHandlerSelectors.any())
	      .apis(RequestHandlerSelectors.basePackage("com.wmdm.linksforyoutube.controllers"))
	      .paths(PathSelectors.any())
	      .build();
	}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API Links for Youtube")
                .description("The REST API of Links for Youtube.").termsOfServiceUrl("")
                .contact(new Contact("Wedson Martins", "", "wedsoncode@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("0.0.1")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
      }

    
    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 
    
    private List<SecurityReference> defaultAuth() {
    	  AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
          AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
          authorizationScopes[0] = authorizationScope; 
          return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
      }
}









