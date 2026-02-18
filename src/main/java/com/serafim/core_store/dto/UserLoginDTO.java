package com.serafim.core_store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @Email
        @NotBlank
        String email,

        @Size(min = 6, max = 20)
        @NotBlank
        String password
) {
}
