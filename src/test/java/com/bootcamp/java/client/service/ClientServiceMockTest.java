package com.bootcamp.java.client.service;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.bootcamp.java.client.domain.Client;
import com.bootcamp.java.client.repository.ClientRepository;

import util.TestUtil;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ClientServiceMockTest {
	
	@Mock
	ClientRepository clientRepository;
	
	@InjectMocks
	ClientService clientService;
	
	
	//*@Before
    /*public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    */
    
    @BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

    ///*
    
    /*
    @Test
    public void whenClientIsOk() throws IOException {
        Client client = TestUtil.readFile("client-search-name-null","mocks", Client.class);

        Mockito.when(clientRepository.findById(any()))
                .thenReturn(Optional.of(client));

        Mono<Client> test = clientService.findById(any()).test();

        test.assertNoErrors();
        test.assertValue(p -> p.getName() == null);
        test.assertValue(x -> x.getPrice() == 200.00);
        test.assertValues(client);

        Mockito.verify(clientRepository,Mockito.times(1)).findById(any());
    }

    @Test
    public void whenProductIsOkFindByIdError() throws IOException {
        ProductEntity productEntity = TestUtil.readFile("product-search-name-null","mocks", ProductEntity.class);

        Mockito.when(productRepository.findById(any()))
                .thenReturn(Optional.of(productEntity));

        TestObserver<ProductEntity> test = productService.findByIdError(any()).test();

    }

    @Test
    public void whenProductNotFound() {
        Mockito.when(productRepository.findById(any()))
                .thenReturn(Optional.empty());

        TestObserver<ProductEntity> test = productService.findByIdError(any()).test();
        test.assertNotComplete();
        test.assertError(Exception.class);
    }

    @Test
    public void anemicTest() throws IOException {
        ProductEntity productEntity = TestUtil.readFile("product-search-name-null","mocks", ProductEntity.class);

        Double priceWithIgv = productEntity.getPriceWithIgv();

        Assertions.assertEquals(236, priceWithIgv);
    }
	*/
	
}
