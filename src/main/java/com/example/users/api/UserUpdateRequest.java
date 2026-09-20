package com.example.users.api;

import com.example.users.domain.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(
        @NotBlank String firstName,
        @NotBlank String familyName,
        @NotBlank String email,
        @NotBlank String identity,
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String province,
        @NotBlank String postalCode,
        @NotNull Role role,
        Boolean active
) {
}
