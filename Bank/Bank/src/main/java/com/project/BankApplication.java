package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//main klasa ne smije biti u odvojenom folderu jer je onda u istom rangu sa ostalim folderima
//da bi radio entity scan, main mora biti iznad svih, tj u com.project
//zato je izbrisan bank folder u kom je bila
@SpringBootApplication
@EntityScan(basePackages = "com.project.models")
@EnableJpaRepositories(basePackages = "com.project.repositories")
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
