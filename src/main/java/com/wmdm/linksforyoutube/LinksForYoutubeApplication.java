package com.wmdm.linksforyoutube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Links fo You Tube API", version = "1.0", description = "This API to Links of videos for You Tube"))
//@SecurityScheme(name = "wmdmAPI", scheme = "basic", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)

public class LinksForYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinksForYoutubeApplication.class, args);
		System.out.println("************************* SENHA: " + new BCryptPasswordEncoder().encode("123"));
	}

}
