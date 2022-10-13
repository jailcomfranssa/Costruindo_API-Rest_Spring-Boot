package br.com.alura.forun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ForunApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForunApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}

}
