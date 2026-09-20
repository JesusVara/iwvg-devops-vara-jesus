package com.example.users.service;

import com.example.users.api.UserResponse;
import com.example.users.api.UserActiveUpdateRequest;
import com.example.users.api.UserUpdateRequest;
import com.example.users.domain.User;
import com.example.users.repository.UserRepository;
import com.example.users.repository.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> findUsers(String search, Boolean billable, Pageable pageable) {
        Specification<User> specification = Specification.where(UserSpecifications.search(search))
                .and(UserSpecifications.billable(billable));

        return userRepository.findAll(specification, pageable)
                .map(UserResponse::from);
    }

    // Feature-3: remove the requested user from the database.
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Feature-4: mark the selected user as active.
    @Transactional
    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found: " + id));
        user.setActive(true);
        userRepository.save(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseGet(() -> {
            User newUser = new User();
            newUser.setId(id);
            return newUser;
        });

        user.setFirstName(request.firstName());
        user.setFamilyName(request.familyName());
        user.setEmail(request.email());
        user.setIdentity(request.identity());
        user.setAddress(request.address());
        user.setCity(request.city());
        user.setProvince(request.province());
        user.setPostalCode(request.postalCode());
        if (request.active() != null) {
            user.setActive(request.active());
        }

        return UserResponse.from(userRepository.save(user));
    }

    @Transactional
    public List<UserResponse> updateUsersActive(List<UserActiveUpdateRequest> requests) {
        List<User> users = requests.stream()
                .filter(UserActiveUpdateRequest::active)
                .map(request -> userRepository.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User not found: " + request.id())))
                .toList();

        users.forEach(user -> user.setActive(true));

        return userRepository.saveAll(users).stream()
                .map(UserResponse::from)
                .toList();
    }
}
