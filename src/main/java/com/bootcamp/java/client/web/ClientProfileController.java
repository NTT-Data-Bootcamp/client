package com.bootcamp.java.client.web;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.java.client.domain.ClientProfile;
import com.bootcamp.java.client.service.ClientProfileService;
import com.bootcamp.java.client.web.mapper.ClientProfileMapper;
import com.bootcamp.java.client.web.model.ClientProfileModel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/clientprofile")
public class ClientProfileController {

	@Value("${spring.application.name}")
	String name;
	
	@Value("${server.port}")
	String port;
	
	@Autowired
	private ClientProfileService clientProfileService;
	
	@Autowired
	private ClientProfileMapper clientProfileMapper;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<ClientProfileModel>>> getAll(){
		log.info("getAll executed");
		return Mono.just(ResponseEntity.ok()
			.body(clientProfileService.findAll()
					.map(clientProfile -> clientProfileMapper.entityToModel(clientProfile))));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ClientProfileModel>> getById(@PathVariable String id){
		log.info("getById executed {}", id);
		Mono<ClientProfile> response = clientProfileService.findById(id);
		return response
				.map(clientProfile -> clientProfileMapper.entityToModel(clientProfile))
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<ClientProfileModel>> create(@Valid @RequestBody ClientProfileModel request){
		log.info("create executed {}", request);
		return clientProfileService.create(clientProfileMapper.modelToEntity(request))
				.map(clientProfile -> clientProfileMapper.entityToModel(clientProfile))
				.flatMap(c ->
					Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
							port, "clientProfile", c.getId())))
							.body(c)))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ClientProfileModel>> updateById(@PathVariable String id, @Valid @RequestBody ClientProfileModel request){
		log.info("updateById executed {}:{}", id, request);
		return clientProfileService.update(id, clientProfileMapper.modelToEntity(request))
				.map(clientProfile -> clientProfileMapper.entityToModel(clientProfile))
				.flatMap(c ->
				Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
						port, "clientProfile", c.getId())))
						.body(c)))
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
		log.info("deleteById executed {}", id);
		return clientProfileService.delete(id)
				.map( r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	

}
