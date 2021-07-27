package com.wmdm.linksforyoutube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LinksForYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinksForYoutubeApplication.class, args);
		System.out.println("************************* SENHA: " + new BCryptPasswordEncoder().encode("123"));
	}

}
