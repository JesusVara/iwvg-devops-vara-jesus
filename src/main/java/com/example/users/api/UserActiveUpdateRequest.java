package com.example.users.api;

import jakarta.validation.constraints.NotNull;

public record UserActiveUpdateRequest(
        @NotNull
        Long id,
        @NotNull
        Boolean active
) {
}
