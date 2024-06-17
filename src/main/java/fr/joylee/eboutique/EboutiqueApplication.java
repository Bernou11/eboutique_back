package fr.joylee.eboutique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "fr.joylee")
public class EboutiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EboutiqueApplication.class, args);
	}

}
