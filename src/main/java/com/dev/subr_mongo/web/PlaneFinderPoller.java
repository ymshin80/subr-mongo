package com.dev.subr_mongo.web;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.dev.subr_mongo.domain.Aircraft;
import com.dev.subr_mongo.repository.AircraftRepository;

import jakarta.annotation.Nonnull;

@Component
@EnableScheduling
public class PlaneFinderPoller {
	
	@Nonnull
	private final AircraftRepository repository;
	private WebClient client = 
			WebClient.create("http://localhost:7643/aircraft");
	
	public PlaneFinderPoller(AircraftRepository repository) {
		this.repository = repository;
	}
	
	@Scheduled(fixedRate = 1000)
	private void pllPlane() {
		repository.deleteAll();
		
		client.get()
			.retrieve()
			.bodyToFlux(Aircraft.class)
			.filter(plane -> !plane.getReg().isEmpty())
			.toStream()
			.forEach(repository::save);
		
		repository.findAll().forEach(System.out::println);
	}
}
