package com.example.users.service;

import com.example.users.api.UserResponse;
import com.example.users.api.UserUpdateRequest;
import com.example.users.domain.User;
import com.example.users.domain.Role;
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
class UserServiceUpdateTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void updatesExistingUserAndPreservesActiveWhenOmitted() {
        User user = new User();
        user.setId(28L);
        user.setActive(true);
        when(userRepository.findById(28L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        UserResponse response = userService.updateUser(28L, request(null));

        assertThat(user.getFirstName()).isEqualTo("Ana");
        assertThat(user.getEmail()).isEqualTo("ana.updated@example.com");
        assertThat(user.isActive()).isTrue();
        assertThat(response.id()).isEqualTo(28L);
        verify(userRepository).save(user);
    }

    @Test
    void createsUserWhenIdDoesNotExist() {
        when(userRepository.findById(28L)).thenReturn(Optional.empty());
        when(userRepository.save(org.mockito.ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = userService.updateUser(28L, request(false));

        assertThat(response.id()).isEqualTo(28L);
        assertThat(response.firstName()).isEqualTo("Ana");
        assertThat(response.active()).isFalse();
        verify(userRepository).save(org.mockito.ArgumentMatchers.argThat(user ->
                user.getId().equals(28L) && "Ana".equals(user.getFirstName())));
    }

    private UserUpdateRequest request(Boolean active) {
        return new UserUpdateRequest(
                "Ana",
                "García",
                "ana.updated@example.com",
                "ID-28",
                "Calle Mayor 1",
                "Madrid",
                "Madrid",
                "28001",
                Role.USER,
                active
        );
    }
}
