package com.example.users.api;

public record UserUpdateRequest(
        String firstName,
        String familyName,
        String email,
        String identity,
        String address,
        String city,
        String province,
        String postalCode,
        Boolean active
) {
}
