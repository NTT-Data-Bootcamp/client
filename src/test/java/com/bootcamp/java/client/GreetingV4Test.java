package com.bootcamp.java.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assumptions.*;


import com.bootcamp.java.client.domain.dto.Greeting;

public class GreetingV4Test {

	private Greeting greeting;

	@BeforeEach
	void setUp() {
		greeting = new Greeting();
	}

	@Test
	void assumeIsTrue() {
		assumeTrue(greeting.helloWorld().equals("Hello World"));
	}

	@Test
	void assumeIsFalse() {
		assumeFalse(greeting.helloWorld().equals("Hello World"));
	}

}
