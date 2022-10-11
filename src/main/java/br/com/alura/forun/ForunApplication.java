package br.com.alura.forun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ForunApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForunApplication.class, args);
	}

}
