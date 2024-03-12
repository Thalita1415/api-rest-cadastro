package br.com.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class ApiRestCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestCadastroApplication.class, args);
	}

}
