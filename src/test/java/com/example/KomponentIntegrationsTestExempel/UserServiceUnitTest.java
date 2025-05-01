package com.example.KomponentIntegrationsTestExempel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
   private UserRepository userRepository;


    @InjectMocks
    private UserService userService;

    @Test
    public void testgetUserByIdReturnsUser() {
        //Arrange
        User user = new User(1L,"bill","@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //Act
        User Result =  userService.findById(1L).orElse(null);

        //Assert
        assertEquals("bill",Result.getName());
        verify(userRepository).findById(1L);
    }


}