package com.example.auth_api_security;


import com.example.auth_api_security.domain.User;
import com.example.auth_api_security.dtos.user.RegisterDTO;
import com.example.auth_api_security.dtos.user.UserRole;
import com.example.auth_api_security.repositories.UsersRepository;
import com.example.auth_api_security.security.TokenService;
import com.example.auth_api_security.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class registerUsersTest {


    @Mock
    private UsersRepository usersRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_UserDoesNotExist_ShouldRegisterUser() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("testUser", "testPassword", UserRole.ADMIN);
        when(usersRepository.findByLogin(registerDTO.login())).thenReturn(null);
        when(usersRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User registeredUser = authenticationService.registerUser(registerDTO);

        // Assert
        if (registeredUser==null) throw new RuntimeException("Valor nulo");
        assertNotNull(registeredUser);
        assertEquals(registerDTO.login(), registeredUser.getLogin());
        assertTrue(new BCryptPasswordEncoder().matches(registerDTO.password(), registeredUser.getPassword()));
        assertEquals(registerDTO.role(), registeredUser.getRole());
        verify(usersRepository, times(1)).findByLogin(registerDTO.login());
        verify(usersRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_UserAlreadyExists_ShouldReturnNull() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("existingUser", "password", UserRole.USER);
        User existingUser = new User("existingUser", "encodedPassword", UserRole.USER);
        when(usersRepository.findByLogin(registerDTO.login())).thenReturn(existingUser);

        // Act
        User result = authenticationService.registerUser(registerDTO);

        assertNull(result);
        verify(usersRepository, times(1)).findByLogin(registerDTO.login());
    }
}