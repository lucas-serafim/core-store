package com.serafim.core_store.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(

        @NotBlank(message = "name is required.")
        @Size(min = 3, message = "size must be greater than 3")
        String name,

        @Email
        @NotBlank(message = "email is required.")
        String email,

        @Size(min = 6, max = 20, message = "size must be greater than 6 and less than 20")
        @NotBlank(message = "password is required.")
        String password
) {
}
