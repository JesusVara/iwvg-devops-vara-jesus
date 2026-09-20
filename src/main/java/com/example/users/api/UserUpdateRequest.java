package com.example.users.api;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String familyName,
        @NotBlank
        String email,
        @NotBlank
        String identity,
        @NotBlank
        String address,
        @NotBlank
        String city,
        @NotBlank
        String province,
        @NotBlank
        String postalCode,
        Boolean active
) {
}
