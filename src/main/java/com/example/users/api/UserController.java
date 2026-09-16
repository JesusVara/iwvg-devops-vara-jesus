package com.example.users.api;

import com.example.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Set<String> SORTABLE_PROPERTIES = Set.of(
            "id", "firstName", "familyName", "email", "identity",
            "address", "city", "province", "postalCode"
    );

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponse> getUsers(@RequestParam(required = false) String search,
                                       @RequestParam(required = false) Boolean billable,
                                       Pageable pageable) {
        return userService.findUsers(search, billable, sanitizePageable(pageable));
    }

    private Pageable sanitizePageable(Pageable pageable) {
        Sort sort = Sort.by(pageable.getSort().stream()
                .filter(order -> SORTABLE_PROPERTIES.contains(order.getProperty()))
                .toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }
}
