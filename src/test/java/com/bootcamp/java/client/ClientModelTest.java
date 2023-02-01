package com.bootcamp.java.client;

//import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Assertions;


import com.bootcamp.java.client.domain.Client;
import com.bootcamp.java.client.web.mapper.ClientMapper;

@SpringBootTest(classes = { ClientMapper.class })
public class ClientModelTest {

	
	@Test
	void validateToString() throws Exception {
		// given
		Client client = Client.builder().id("112345523").name("CARLOS").lastName("JUAREZ SALAZAR").clientType("PERSONNEL").build();

		// when
		assertNotNull(client.toString());
	}

	@Test
	void validateHashCode() throws Exception {
		// given
		Client client = Client.builder().id("112345523").name("CARLOS").lastName("JUAREZ SALAZAR").clientType("PERSONNEL").build();

		// when
		assertNotNull(client.hashCode());
	}
	
	@Test
	void validateFieldClienType() throws Exception {
		// given
		Client client = Client.builder().id("112345523").name("CARLOS").lastName("JUAREZ SALAZAR").clientType("PERSONNEL").build();
		
		assertEquals(client.clientType, "BUSINESS");
		
	}
	

}
