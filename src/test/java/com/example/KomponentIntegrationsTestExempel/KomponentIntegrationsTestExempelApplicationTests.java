package com.example.KomponentIntegrationsTestExempel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KomponentIntegrationsTestExempelApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void testCreateandFetchUser() {
		//Arange
		User user = new User(1L,"bill","@gmail.com");


		//act
		User savedUser = userService.createUser(user);
		User fetchedUser = userService.findById(savedUser.getId()).orElse(null);

		//assert
		assertEquals("bill", fetchedUser.getName());


	}

}
