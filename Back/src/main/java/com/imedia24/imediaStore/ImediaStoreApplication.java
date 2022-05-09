package com.imedia24.imediaStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ImediaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImediaStoreApplication.class, args);
	}

}
