package com.serafim.core_store.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @Email
        @NotBlank(message = "email is required.")
        String email,

        @NotBlank(message = "password is required.")
        String password
) {
}
