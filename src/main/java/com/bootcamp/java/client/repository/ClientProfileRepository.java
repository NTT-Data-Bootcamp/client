package com.bootcamp.java.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.java.client.domain.ClientProfile;

import ch.qos.logback.core.net.server.Client;
import reactor.core.publisher.Mono;

@Repository
public interface ClientProfileRepository extends ReactiveMongoRepository<ClientProfile, String>{
	Mono<ClientProfile> findTopByIdAndClientType(String id, String clientType);
	
}
