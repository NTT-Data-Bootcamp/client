package com.bootcamp.java.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.java.client.domain.Client;
import com.bootcamp.java.client.domain.dto.ClientDocumentTypeEnum;
import com.bootcamp.java.client.domain.dto.ClientTypeEnum;
import com.bootcamp.java.client.repository.ClientProfileRepository;
import com.bootcamp.java.client.repository.ClientRepository;
import com.bootcamp.java.client.service.exception.InvalidClientDocumentTypeException;
import com.bootcamp.java.client.service.exception.InvalidClientProfileException;
import com.bootcamp.java.client.service.exception.InvalidNameClientException;
import com.bootcamp.java.client.web.mapper.ClientMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private ClientProfileRepository clientProfileRepository;
	
	/*
	@Autowired
	private ClientProfileService clientProfileService;
	*/

	public Flux<Client> findAll(){
		log.debug("findAll executed");
		return clientRepository.findAll();
	}

	public Mono<Client> findById(String clientId){
		log.debug("findById executed {}", clientId);
		return clientRepository.findById(clientId);
	}

	public Mono<Client> create(Client client){
		log.debug("create service executed {}", client);
		
		return validateDocumentTypeAndClientType(client)
				.switchIfEmpty(Mono.error(new InvalidClientDocumentTypeException()))
				.flatMap(cli -> {
					return validateNameByClientType(cli)
							.switchIfEmpty(Mono.error(new InvalidNameClientException(cli.clientType)))
							//.flatMap(dbClient -> clientRepository.save(dbClient));
							.flatMap(dbClient -> {								
								return clientProfileRepository.findTopByIdAndClientType(cli.getIdClientProfile(), cli.getClientType())	
										.switchIfEmpty(Mono.error(new InvalidClientProfileException()))
										.flatMap(cliProfile -> clientRepository.save(dbClient));									
										
							});
				});
		//return customerRepository.save(customer);
	}
	
	private Mono<Client> validateDocumentTypeAndClientType(Client client) {
		
		boolean documentTypeIsValid = (client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.DNI.getValue()) ||
				client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.CARNET_EXTRANJERIA.getValue()) ||
				client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.PASAPORTE.getValue()) ||
				client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.RUC.getValue()));
		
		if(documentTypeIsValid) {			
			//client.setClientType(client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.RUC.getValue()) ? ClientTypeEnum.BUSINESS.getValue(): ClientTypeEnum.PERSONNEL.getValue() );
			if( client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.RUC.getValue()) &&
				client.getClientType().trim().equals(ClientTypeEnum.BUSINESS.getValue())	
			) {				
				client.setName(null);
				client.setLastName(null);
				//client.setClientType( ClientTypeEnum.BUSINESS.getValue() );
				return Mono.just(client);
			}
			else if( (client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.DNI.getValue()) ||
					client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.CARNET_EXTRANJERIA.getValue()) ||
					client.getIdentityDocumentType().trim().equals(ClientDocumentTypeEnum.PASAPORTE.getValue())					
					) &&
					client.getClientType().trim().equals(ClientTypeEnum.PERSONNEL.getValue())	
					) {
				client.setBusinessName(null);
				return Mono.just(client);
				//client.setClientType( ClientTypeEnum.PERSONNEL.getValue() );				
			}
			else {
				return Mono.error(new Exception("This type of client document and/or Client Type are invalid"));
			}
		}
		
		return Mono.error(new InvalidClientDocumentTypeException());	
		
		//return documentTypeIsValid ? Mono.just(client): Mono.error(new InvalidClientDocumentTypeException());		
	}
	
	private Mono<Client> validateNameByClientType(Client client) {
		boolean nameIsValid = client.getClientType().trim().equals(ClientTypeEnum.BUSINESS.getValue())
				? client.getBusinessName() != null && !client.getBusinessName().isEmpty()
				: client.getName() != null && !client.getName().isEmpty() && client.getLastName() != null && !client.getLastName().isEmpty();
		return nameIsValid ? Mono.just(client) : Mono.error(new InvalidNameClientException(client.getClientType().trim()));
	}
	

	public Mono<Client> update(String clientId, Client client){
		/*log.debug("update executed {}:{}", clientId, client);
		return clientRepository.findById(clientId)
		.flatMap(dbClient -> {
			clientMapper.update(dbClient, client);
			return clientRepository.save(dbClient);
		});
		*/
		log.debug("update executed {}:{}", clientId, client);
		return clientRepository.findById(clientId)
		.flatMap(cli -> {
			clientMapper.update(cli, client);
			return validateDocumentTypeAndClientType(cli)
					.switchIfEmpty(Mono.error(new InvalidClientDocumentTypeException()))
					.flatMap(cli2 -> {
						return validateNameByClientType(cli2)
								.switchIfEmpty(Mono.error(new InvalidNameClientException(cli.clientType)))
								//.flatMap(dbClient -> clientRepository.save(dbClient));
								.flatMap(dbClient -> {								
									return clientProfileRepository.findTopByIdAndClientType(cli2.getIdClientProfile(), cli2.getClientType())	
											.switchIfEmpty(Mono.error(new InvalidClientProfileException()))
											.flatMap(cliProfile -> clientRepository.save(dbClient));									
									
								});
					});
		});
	}

	public Mono<Client> delete(String clientId){
		log.debug("delete executed {}", clientId);
		return clientRepository.findById(clientId)
		.flatMap(existingClient -> clientRepository.delete(existingClient)
		.then(Mono.just(existingClient)));
		}

	public Mono<Client> findTopByIdentityDocumentNumberAndIdentityDocumentType(String identityDocumentNumber, String identityDocumentType){
		log.debug("findById executed {} {}", identityDocumentNumber, identityDocumentType);
		return clientRepository.findTopByIdentityDocumentNumberAndIdentityDocumentType(identityDocumentNumber, identityDocumentType);
	}
} 
