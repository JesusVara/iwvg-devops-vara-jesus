package com.example.users.service;

import com.example.users.domain.User;
import com.example.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceActivateTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void activatesUserById() {
        User user = new User();
        when(userRepository.findById(28L)).thenReturn(Optional.of(user));

        userService.activateUser(28L);

        assertThat(user.isActive()).isTrue();
        verify(userRepository).save(user);
    }
}
