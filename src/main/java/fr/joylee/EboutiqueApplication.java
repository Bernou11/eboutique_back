package fr.joylee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fr.joylee", exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "fr.joylee.repositories")
public class EboutiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EboutiqueApplication.class, args);
	}

}
