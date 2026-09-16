package com.example.users.service;

import com.example.users.api.UserResponse;
import com.example.users.domain.User;
import com.example.users.repository.UserRepository;
import com.example.users.repository.UserSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> findUsers(String search, Boolean billable, Pageable pageable) {
        Specification<User> specification = Specification.where(UserSpecifications.search(search))
                .and(UserSpecifications.billable(billable));
        return userRepository.findAll(specification, pageable).map(UserResponse::from);
    }
}
