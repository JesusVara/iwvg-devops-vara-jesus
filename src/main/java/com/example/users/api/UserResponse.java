package com.example.users.api;

import com.example.users.domain.User;
import com.example.users.domain.Role;

public record UserResponse(
        Long id,
        String firstName,
        String familyName,
        String email,
        String identity,
        String address,
        String city,
        String province,
        String postalCode,
        Role role,
        boolean billable,
        boolean active
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getFamilyName(),
                user.getEmail(),
                user.getIdentity(),
                user.getAddress(),
                user.getCity(),
                user.getProvince(),
                user.getPostalCode(),
                user.getRole(),
                user.isBillable(),
                user.isActive()
        );
    }
}
