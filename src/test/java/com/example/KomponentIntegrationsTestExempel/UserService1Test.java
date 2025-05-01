package com.example.KomponentIntegrationsTestExempel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserService1Test {

    @Mock
    private UserRepository1 userRepository;

    @InjectMocks
    private UserService1 userService;

    @Test
    public void testIfUserEmailDoesExistIfNotSaveEmail() {

   String name = "Test User";
   String email = "test@test.com";

   User user = new User(1l, name, email);

      when(userRepository.existsByEmail(email)).thenReturn(false);
      when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);



      User Result = userService.createUser(name, email);

      assertEquals(name,Result.getName());
      assertEquals(email,Result.getEmail());

      verify(userRepository).save(ArgumentMatchers.any(User.class));
      verify(userRepository).existsByEmail(email);

    }

    @Test
    public void testIfEmailDoesExistThrowIllegalArgumentException() {
        String name = "Test User";
        String email = "test@test.com";

       when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
                    userService.createUser(name, email);
                });

        verify(userRepository).existsByEmail(email);
        verify(userRepository,never()).save(ArgumentMatchers.any(User.class));

    }


}