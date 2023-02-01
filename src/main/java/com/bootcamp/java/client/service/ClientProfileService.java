package com.bootcamp.java.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.java.client.domain.ClientProfile;
import com.bootcamp.java.client.repository.ClientProfileRepository;
import com.bootcamp.java.client.web.mapper.ClientProfileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClientProfileService {

	@Autowired
	private ClientProfileRepository clientProfileRepository;
	@Autowired
	private ClientProfileMapper clientProfileMapper;


	public Flux<ClientProfile> findAll(){
		log.debug("findAll executed in service");
		return clientProfileRepository.findAll();
	}

	public Mono<ClientProfile> findById(String clientProfileId){
		log.debug("findById executed in service {}", clientProfileId);
		return clientProfileRepository.findById(clientProfileId);
	}

	public Mono<ClientProfile> create(ClientProfile clientProfile){
		log.debug("create ClientProfile executed in service {}", clientProfile);
		
		return clientProfileRepository.save(clientProfile);
	}	

	public Mono<ClientProfile> update(String clientProfileId, ClientProfile clientProfile){
		log.debug("update ClientProfile executed in service {}:{}", clientProfileId, clientProfile);
		return clientProfileRepository.findById(clientProfileId)
		.flatMap(dbClientProfile -> {
			clientProfileMapper.update(dbClientProfile, clientProfile);
			return clientProfileRepository.save(dbClientProfile);
		});
	}

	public Mono<ClientProfile> delete(String clientProfileId){
		log.debug("delete ClientProfile executed in service {}", clientProfileId);
		return clientProfileRepository.findById(clientProfileId)
		.flatMap(existingClientProfile -> clientProfileRepository.delete(existingClientProfile)
		.then(Mono.just(existingClientProfile)));
		}

	public Mono<ClientProfile> findTopByIdAndClientType(String id, String clientType){
		log.debug("findTopByIdAndClientType executed in service {} {}", id, clientType);
		return clientProfileRepository.findTopByIdAndClientType(id, clientType);
	}
	
}
