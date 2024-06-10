package com.dev.subr_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.dev.subr_mongo.repository")
public class SubrMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubrMongoApplication.class, args);
	}

}
