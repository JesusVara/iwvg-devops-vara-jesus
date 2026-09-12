package com.example.users.api;

import com.example.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponse> getUsers(@RequestParam(required = false) String search,
                                       @RequestParam(required = false) Boolean billable,
                                       Pageable pageable) {
        return userService.findUsers(search, billable, pageable);
    }
}
