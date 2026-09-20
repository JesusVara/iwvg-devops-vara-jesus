package com.example.users.service;

import com.example.users.api.UserActiveUpdateRequest;
import com.example.users.api.UserResponse;
import com.example.users.domain.User;
import com.example.users.domain.Role;
import com.example.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceActiveListTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void activatesOnlyUsersWithActiveTrue() {
        User user = new User();
        user.setId(28L);
        user.setRole(Role.USER);
        when(userRepository.findById(28L)).thenReturn(Optional.of(user));
        when(userRepository.saveAll(List.of(user))).thenReturn(List.of(user));

        List<UserResponse> response = userService.updateUsersActive(List.of(
                new UserActiveUpdateRequest(28L, true)
        ));

        assertThat(user.isActive()).isTrue();
        assertThat(response).extracting(UserResponse::id).containsExactly(28L);
        verify(userRepository).saveAll(List.of(user));
    }

    @Test
    void doesNotInactivateAdminUsers() {
        User admin = new User();
        admin.setId(28L);
        admin.setRole(Role.ADMIN);
        admin.setActive(true);
        when(userRepository.findById(28L)).thenReturn(Optional.of(admin));

        List<UserResponse> response = userService.updateUsersActive(List.of(
                new UserActiveUpdateRequest(28L, false)
        ));

        assertThat(response).isEmpty();
        assertThat(admin.isActive()).isTrue();
        verify(userRepository).findById(28L);
    }
}
