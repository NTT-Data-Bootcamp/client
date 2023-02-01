package com.bootcamp.java.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.java.client.repository.ClientRepository;
import com.bootcamp.java.client.service.ClientService;
import com.bootcamp.java.client.web.mapper.ClientMapper;

@ExtendWith(MockitoExtension.class)
public class MockitoTestIT_V3_InyectarMocksTest {

	@Mock
	ClientRepository clientRepository;
	
	@Mock
	ClientMapper clientMapper;
	
	@InjectMocks
	ClientService clientService;
	
	
    @Test
    void testMock() throws Exception {
    	clientService.findAll();
    }

}
