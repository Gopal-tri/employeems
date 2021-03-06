package com.trainingbasket.employeems.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.trainingbasket.employeems.login.model.Role;
import com.trainingbasket.employeems.login.model.User;
import com.trainingbasket.employeems.login.repository.RoleRepository;
import com.trainingbasket.employeems.login.repository.UserRepository;
import com.trainingbasket.employeems.login.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Arrays;

class UserServiceTest {
	@Mock
	private UserRepository mockUserRepository;

	@Mock
	private RoleRepository mockRoleRepository;

	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;

	private UserService userServiceUnderTest;

	private User user;

	@Before
	public void setUp() {
		initMocks(this);
		userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
		user = User.builder()
				.id(1)
				.name("Gustavo")
				.lastName("Ponce")
				.email("test@test.com")
				.active(true)
				.mobile("9718910927")
				.address("Delhi").build();
		
		
		
		Mockito.when(mockUserRepository.save(any())).thenReturn(user);
		Mockito.when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
	}

	@Test
	public void testFindUserByEmail() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		final User result = userServiceUnderTest.findUserByEmail(email);

		// Verify the results
		assertEquals(email, result.getEmail());
	}

	@Test
	public void testSaveUser() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		User result = userServiceUnderTest.saveUser(User.builder().build());

		// Verify the results
		assertEquals(email, result.getEmail());
	}

}
