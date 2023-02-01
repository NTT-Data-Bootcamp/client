package com.bootcamp.java.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import com.bootcamp.java.client.domain.dto.Greeting;

public class GreetingV3Test {
	private Greeting greeting;

	@BeforeEach
	void setUp() {
		greeting = new Greeting();
	}

	@Test
	void greetingWithName() {
		assertEquals("Hello BootCamp BC25", greeting.helloWorld("BootCamp BC25"));
	}

	private void assertEquals(String string, String helloWorld) {
		// TODO Auto-generated method stub
		
	}

	@Test
	void greetingDefault() {
		assertEquals("Hello World", greeting.helloWorld());
		assertNotEquals("Hello BootCamp BC25", greeting.helloWorld());
	}

	@Test
	@Disabled
	void conditionalTest() {
		assertTrue(greeting.helloWorld().equals("Hello World"));
		assertFalse(greeting.helloWorld("BootCamp BC25").equals("BootCamp BC25"));
	}

	@Test
	@DisplayName("Test exception")
	void oupsHandler() {
		assertThrows(ArithmeticException.class, () -> {
			double result = 5 / 3;
		});
	}

}
