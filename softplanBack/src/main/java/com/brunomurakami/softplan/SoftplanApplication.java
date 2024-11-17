package com.brunomurakami.softplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.brunomurakami.softplan")
@EntityScan(basePackages = "com.brunomurakami.softplan.model")
@EnableCaching
public class SoftplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftplanApplication.class, args);
	}

}
