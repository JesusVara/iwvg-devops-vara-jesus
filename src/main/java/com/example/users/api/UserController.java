package com.example.users.api;

import com.example.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.Set;

@RestController
@RequestMapping({"/user"}) //Just /user url
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

    // Feature-3: delete a user by identifier.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Feature-4: activate a user by identifier.
    @PutMapping("/{id}/active")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.activateUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update a user",
            description = "Replaces the editable data of the selected user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    private Pageable sanitizePageable(Pageable pageable) {
        Sort sort = Sort.by(pageable.getSort().stream()
                .filter(order -> SORTABLE_PROPERTIES.contains(order.getProperty()))
                .toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }
}
