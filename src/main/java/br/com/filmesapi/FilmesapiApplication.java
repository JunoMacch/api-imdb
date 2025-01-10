package br.com.filmesapi;

import br.com.filmesapi.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmesapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
